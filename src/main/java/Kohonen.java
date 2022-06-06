import java.text.DecimalFormat;
import java.util.*;

public class Kohonen {

    public static List<Neuron> all = new ArrayList<>();

    public static double alpha = 1;//скорость обучения
    public static double sigma = 1;//радиус соседской функции
    public static double lambda = 0.5;//константа, используемая для изменения значений радиуса и скорости обучения
    public static double potentialMin = 0.5;//минимальный потенциал
    public static int size_features = 9;//количество фичей
    // sizes of grid
    public static int m = 4;
    public static int n = 4;

    public static void execute(Map<String, double[]> data){


        for (int i = 0; i < m; i++) {  // заполняем двумерный массив
            for(int j = 0; j < n; j++) {
                Neuron con = new Neuron();
                con.setX(i);
                con.setY(j);
                all.add(con);
            }
        }
        int epochs = 100;
        for(int i = 0; i < epochs; i++) {  // обучение
            for (String key : data.keySet()) {
                learn(data.get(key));
            }
            sigma = sigma*Math.exp(-i/lambda);
            alpha = alpha*Math.exp(-i/lambda);
        }

        System.out.println("Мощность");
        getMap(data, 0);
        System.out.println();

        System.out.println("Индекс массы");
        getMap(data, 1);
        System.out.println();

        System.out.println("Индекс цены");
        getMap(data, 2);
        System.out.println();

        System.out.println("Индекс комфорт");
        getMap(data, 3);
        System.out.println();

        System.out.println("Индекс безопасности");
        getMap(data, 4);
        System.out.println();

        System.out.println("Индекс проходимости");
        getMap(data, 5);
        System.out.println();
        System.out.println("Индекс вместимости");
        getMap(data, 6);
        System.out.println();
//
        System.out.println("Индекс скорости");
        getMap(data, 7);
        System.out.println();
    }

    public static void learn(double[] inputs) {
        inputs = normalization(inputs);

        //проставляем инпуты
        for (Neuron neuron : all) {
            neuron.setInputs(inputs);
        }

        double[] distance = distance();  //считается дистанция до наблюдения

        double min = distance[0];
        int winNeuron = 0;

        // находим аргмин при этом пропускаем те у которых потенциал меньше potentialMin
        for(int i = 0; i < distance.length; i++) {
            if(all.get(i).getPotential() < potentialMin) {
                continue;
            }

            if (distance[i] < min) {
                min = distance[i];
                winNeuron = i;
            }
        }

        //двигаем победителя
        for(int i = 0; i < size_features; i++) {
            double weight = all.get(winNeuron).getWeight(i);
            double input = all.get(winNeuron).getInput(i);
            double res = weight + alpha * (input - weight);
            all.get(winNeuron).setWeight(i, res);
        }

        // меняем потенциалы
        for (int i = 0; i < all.size(); i++) {
            double X1, Y1, Xw, Yw;
            X1 = all.get(i).getX();
            Y1 = all.get(i).getY();
            Xw = all.get(winNeuron).getX();
            Yw = all.get(winNeuron).getY();

            if(X1 - Xw <= sigma || X1 - Xw >= -sigma || Y1 - Yw <= sigma || Y1 - Yw >= -sigma) {
                double dist = (X1 - Xw)*(X1 - Xw) + (Y1 - Yw)*(Y1 - Yw);
                if(dist == 0) {  //это победитель
                    all.get(winNeuron).setPotential(all.get(winNeuron).getPotential() - potentialMin);
                }
                else{   //это не победитель
                    all.get(i).setPotential(all.get(i).getPotential() + 1/all.size());   //добавляем потенциал
                    double beta = Math.exp(-dist/(2*sigma*sigma));   //чтобы соседа подвинуть поменьше, чем победителя
                    for(int j = 0; j < size_features; j++) {
                        double weight = all.get(i).getWeight(j);
                        double input = all.get(i).getInput(j);
                        double res = weight + alpha * beta * (input - weight);
                        all.get(i).setWeight(j, res);
                    }
                }
            }
        }
    }

    public static double[] distance () {  //считает дистанцию всех нейронов до переданного наблюдения
        double[] res = new double[all.size()];
        double sum = 0;
        for(int i = 0; i < all.size(); i++) {
            sum = 0;
            for(int j = 0; j < size_features; j++) {
                sum += (all.get(i).getInput(j) - all.get(i).getWeight(j)) *
                        (all.get(i).getInput(j) - all.get(i).getWeight(j));
            }
            res[i] = Math.sqrt(sum);
        }
        return res;
    }

    public static double[] normalization(double[] inputs) {  //нормализация
        double sum = 0;
        for (double input : inputs) {
            sum += input * input;
        }
        for(int i = 0; i < inputs.length; i++) {
            inputs[i] = inputs[i]/Math.sqrt(sum);
        }
        return inputs;
    }


    public static void getMap(Map<String, double[]> data, int index) {
        double[] res = new double[all.size()];
        final DecimalFormat decimalFormat = new DecimalFormat("0.000");
        Map<Integer, List<double[]>> map = new HashMap<>();    //нейроны, хранящие входящие в них записи(для которых он победитель)
        for (int i = 0; i < all.size(); i++) {
            map.put(i, new ArrayList<>());
        }

        for (String key : data.keySet()) {      //проходимся по обучающему множеству
            double[] inputs = data.get(key);   //гетаем параметры объекта
            for (int i = 0; i < all.size(); i++) {      //идем по нейронам
                double sum = 0;
                for (int j = 0; j < size_features; j++) {
                    sum += Math.pow((inputs[j] - all.get(i).getWeight(j)), 2);   //считаем у всех нейронов дистанцию
                }
                res[i] = Math.sqrt(sum);
            }

            double minDistance = res[0];
            int neuronWinner = 0;

            for (int i = 0; i < res.length; i++) {
                if (res[i] < minDistance) {
                    minDistance = res[i];
                    neuronWinner = i;
                }
            }

            map.get(neuronWinner).add(inputs);    //добавляем это наблюдение в победителя
        }

        for (int i = 0; i < all.size(); i++) {                                //отрисовка
            if (i != 0 && i % n == 0) {
                System.out.println();
            }
            double summ = 0;
            for (double[] dd : map.get(i)) {
                summ += dd[index];               //получаем сумму значений всех наблюдений этого нейрона
            }
            if (summ != 0)
                summ /= map.get(i).size();  //делим сумму на количество и получаем avg

            System.out.print(decimalFormat.format(summ) + " ");
        }
    }
}
