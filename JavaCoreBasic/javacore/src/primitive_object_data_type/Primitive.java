package primitive_object_data_type;

public class Primitive {
    public static void main(String[] args){
        // auto-boxing
        double a= 5.5;
        Double b= a;
        System.out.println(b);

        // auto-unboxing
        double c= b;
        System.out.println(c);

        // thủ công
        Double d = Double.valueOf(a);
        float f = d.floatValue();
        System.out.printf("d = %.2f and f = %.2f\n",d,f);

        // chuyển đổi với string
        String s= "1234";
        int i1= Integer.parseInt(s);
        Integer i2= Integer.valueOf(s);

        String s2 = String.valueOf(i1);
        String s3 = Integer.toString(i1);


        // so sánh
        int x = 1000;
        Integer y = 1000;
        System.out.println(x==y);// true vì java tự auto unboxing y

        if (y.equals(x))
        System.out.println("bằng nhau");

        Integer k = 128;
        Integer l = 128;
        System.out.println(k == l);// false vì khác object


        System.out.println(numDefault);
    }

    static float numDefault;
}
