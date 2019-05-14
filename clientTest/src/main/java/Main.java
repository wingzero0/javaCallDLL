import com.localtest.jnatest.HelloJNA;

public class Main {
    public static void main(String[] args) {
        HelloJNA.CLibrary.INSTANCE.printf("Hello, World\n");
        for (int i=0;i < args.length;i++) {
            HelloJNA.CLibrary.INSTANCE.printf("Argument %d: %s\n", i, args[i]);
        }
    }
}
