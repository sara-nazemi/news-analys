import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<? super Long> as = new ArrayList<Number>();
        as.add(null);
        as.add(12l);
    }

    public void te(final int t) {

    }

    interface BB {
        void ttt();
    }
    interface AA  extends BB {
        int b =1;
        static int c = 2;
        final int d = 1;

        @Override
        default void ttt(){

        }

        static void test() {

        }
    }
}
