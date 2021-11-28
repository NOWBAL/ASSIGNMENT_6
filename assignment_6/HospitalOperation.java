package assignment_6;

public class HospitalOperation {
    private static HospitalOperation _instance;
    private static HospitalOperation
            _instanceForDoubleCheckLocking;
    private boolean empty = false;
    private String patientName = "default";

    private HospitalOperation()
    {
        System.out.println("Instance Created");
    }

    public static synchronized HospitalOperation
    getInstanceSynchronizedWay()
    {

        if (_instance == null)
            _instance = new HospitalOperation();

        return _instance;
    }

    public static HospitalOperation
    getInstanceSynchronizedBlockWay()
    {

        // Checking for double locking
        if (_instanceForDoubleCheckLocking == null)
            synchronized (HospitalOperation.class)
            {
                if (_instanceForDoubleCheckLocking == null)
                    _instanceForDoubleCheckLocking
                            = new HospitalOperation();
            }

        return _instanceForDoubleCheckLocking;
    }

    // Method 4
    // Checks if operation theatre is empty or not
    public boolean isOperationTheatreEmpty()
    {
        return empty;
    }

    // Method 5
    // Called when Operation is finished
    public void endOperation() { empty = true; }

    // Method 6
    // Accessed by more than one threads
    public synchronized void operation(String aName)
    {

        // When flag variables changes from false to true
        if (empty == true) {
            patientName = aName;

            // Get the patient ready as operation can be
            // performed
            System.out.println("Operation can be done "
                    + "get ready patient "
                    + patientName);
            empty = false;
        }

        else {
            System.out.println(
                    "Sorry " + aName
                            + " Operation Theatre is busy with Surgery of "
                            + patientName);
        }
    }
}



