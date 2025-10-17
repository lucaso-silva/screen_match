package calculus;

public class SuggestFilter {
    private String suggest;

    public void filter(Classify classify){
        if(classify.getFinalRate() >= 4){
            System.out.println("This is in hype now");
        }else if(classify.getFinalRate() >= 2){
            System.out.println("A good rate");
        }else{
            System.out.println("You can watch it latter");
        }
    }
}
