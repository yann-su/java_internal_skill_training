package log4j2;

public class EssentialExeception {


    public static class Backbook{

        private final String[] courses = {"广告","优惠券"};

        public String course(int index){
            return index > courses.length ? null : courses[index-1];
        }


    }


}
