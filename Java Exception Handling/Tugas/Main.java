public class Main {
    public static void main(String[] args){

        HospitalQueue queue =  new HospitalQueue();
        boolean continueLoop = true;

        while (continueLoop) {
            continueLoop = queue.runQueue(args);
        }
    }
}
