import src.main.java.Logistic.LogisticCom;

public class Main {
    public static void main(String[] args) {
        try {
            //src.main.java.Logistic.Vehicle.VehicleComponentsCom.sendPostRequest();

            // create the LogisticsCom object
            LogisticCom logisticsCom = new LogisticCom();

            // send POST request for weights
            LogisticCom.sendPostRequest(LogisticCom.API_WEIGHTS_URL);

            // send POST request for dimensions
            LogisticCom.sendPostRequest(LogisticCom.API_DIMENSIONS_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




}