public class Main {
    public static void main(String[] args) {
        try {
            VehicleComponentsCom.sendPostRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogisticCom logisticCom = new LogisticCom();
        System.out.println(logisticCom.getWeights("983.493", "A1A, B2B, C3C", "2023-03-24T13:36:08.943Z"));
    }
}