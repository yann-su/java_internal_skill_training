package mode;

//类与类之间的关系越密切，耦合度越大，当一个类发生改变时，对另一个类的影响也越大。解耦
public class LowOfDemeter {
    public static void main(String[] args) {
        Agent agent = new Agent();
        agent.setStar(new Star("胡歌"));
        agent.setFans(new Fans("张三"));
        agent.setCompany(new Company(""));
        agent.meeting();
        agent.bussiness();
    }
}

class Agent{

    private Star star;
    private Fans fans;
    private Company company;

    public void setStar(Star star) {
        this.star = star;
    }

    public void setFans(Fans fans) {
        this.fans = fans;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void meeting(){
        System.out.println(fans.getName()+"与明星"+star.getName()+"见面");
    }

    public void bussiness(){
        System.out.println(company.getName()+"与明星"+star.getName()+"洽谈业务");
    }

}

class Star{
    private String name;

    public Star(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Fans{
    private String name;

    public Fans(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


class Company{
    private String name;
    public Company(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}