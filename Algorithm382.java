import java.util.ArrayList;

/*
    References
    C Implementation -> http://www.netlib.no/netlib/toms/382
    Original Paper -> https://dl.acm.org/citation.cfm?id=362502
*/

public class Algorithm382 {
    public static void main(String[] args) {
        ArrayList<Integer> objects = new ArrayList<Integer>();
        ArrayList<Integer> lastMElements = new ArrayList<Integer>();
        ArrayList<Integer> array = new ArrayList<Integer>();

        final int N = 20;
        final int M = 6;

        for(int i = 0; i < N; i++)
            objects.add(i);

        for(int i = 0; i < M; i++)
            lastMElements.add(objects.get(N - M + i));

        initializeRotation(N, M, array);

        RotateData rd = new RotateData();
        rd.x = 0;
        rd.y = 0;
        rd.z = 0;
        rd.data = array;

        System.out.println(lastMElements.toString());
        while(rotate(rd) == true) {
            lastMElements.set(rd.z, objects.get(rd.x));
            System.out.println(lastMElements.toString());

            //System.out.println("rd.x:" + rd.x + " --- rd.y: " + rd.y + " --- rd.z: " + rd.z + "\nrd.data: " + rd.data.toString());
        }
    }

    public static void initializeRotation(int N, int M, ArrayList<Integer> array) {
        array.clear();
        array.add(N + 1);

        for(int i = 1; i < N - M + 1; i++)
            array.add(0);

        for(int i = N - M + 1; i < N + 1; i++)
            array.add(i + M - N);

        array.add(-2);

        if(M == 0)
            array.set(1, 1);
    }

    public static boolean rotate(RotateData rd) {
        int i,  j, k;

        for(j = 1; rd.data.get(j) <= 0; j++);

        if(rd.data.get(j - 1) == 0) {
            for(i = j - 1; 1 < i; i--)
                rd.data.set(i, -1);

            rd.data.set(j, 0);
            rd.x = 0;
            rd.z = 0;
            rd.data.set(1, 1);
            rd.y = j - 1;
        }
        else {
            if(j > 1)
                rd.data.set(j - 1, 0);

            for(; rd.data.get(j) > 0; j++);

            k = j - 1;
            i = j;

            for(; rd.data.get(i) == 0; i++)
                rd.data.set(i, -1);

            if(rd.data.get(i) == -1) {
                rd.data.set(i, rd.data.get(k));
                rd.z = rd.data.get(k) - 1;
                rd.x = i - 1;
                rd.y = k - 1;
                rd.data.set(k, -1);
            }
            else {
                if(i == rd.data.get(0))
                    return false;
                else {
                    rd.data.set(j, rd.data.get(i));
                    rd.z = rd.data.get(i) - 1;
                    rd.data.set(i, 0);
                    rd.x = j - 1;
                    rd. y = i - 1;
                }
            }
        }
        return true;
    }
}
