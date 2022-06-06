import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, double[]> data = new HashMap<>();
        //                                                  power, mass, price, comfort, safety, off-road, capacity, speed, ecology
        data.put("Chevrolet Camaro zl1", new double[]      {362.75, 48.75, 357.29, 249.85, 162.65, 7.09,  72.17, 322.48, 181.87});
        data.put("Pagani Zonda R", new double[]            {511.45, 20,    577.36, 74.97,  72.73,  7.40,  2.92,  452.19, 59.73});
        data.put("Aston Martin One-77", new double[]       {434.97, 60.09, 477.92, 372.24, 19.98,  10.60, 79.37, 342.90, 189.90});
        data.put("Dodge Charger SRT-8 DAEMON", new double[]{342.75, 51.92, 324.34, 212.57, 152.65, 5.14,  58.17, 313.74, 81.87});

        data.put("MAN TGS", new double[]                   {381.38, 453.86, 350.12, 268.97, 270.39, 79.59,  530.98, 78.48, 274.46});
        data.put("ЗИЛ 130", new double[]                   {167.68, 347.37, 179.67, 32.47,  51.61,  129.15, 486.65, 38.05, 66.24});
        data.put("Volvo FH", new double[]                  {294.22, 419.26, 382.51, 269.9,  354.56, 93.87,  539.84, 63.91, 315.84});
        data.put("Mercedes Atron", new double[]            {312.72, 399.33, 375.56, 263.57, 252.93, 69.45,  528.64, 75.49, 328.08});

        data.put("Renault Duster", new double[]            {161.29, 195.40, 96.05,  212.19, 177.08, 280.09, 235.73, 130.41, 89.29});
        data.put("Ford F-150", new double[]                {259.47, 202.07, 278.58, 285.34, 159.69, 317.5,  331.69, 163.91, 82.9});
        data.put("Dodge Ram", new double[]                 {259.24, 283.85, 269.61, 250.70, 154.98, 356.33, 321.64, 166.23, 92.23});
        data.put("BMW X-5", new double[]                   {256.17, 149.15, 284.41, 269.25, 200.4,  291.18, 242.51, 173.09, 157.45});

        data.put("Renault Fluence", new double[]           {168.72, 114.27, 93.82,  256.19, 132.03, 184.94, 229.09, 160.69, 164.33});
        data.put("Audi A4", new double[]                   {197.46, 103.18, 161.17, 255.49, 157.64, 139.68, 163.63, 172.4,  121.8});
        data.put("Volkswagen Passat", new double[]         {184.05, 111.42, 123.92, 233.35, 142.11, 143.92, 142.44, 169.19, 165.98});
        data.put("Kia Sportage", new double[]              {134.55, 116.73, 84.11,  202.83, 141.34, 133.04, 135.32, 156.33, 101.19});

        Kohonen.execute(data);
    }
}
