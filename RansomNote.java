import java.util.*;

public class RansomNote{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();

        String magazine = in.nextLine();
        String note = in.nextLine();

        in.close();

        RansomSolution test = new RansomSolution(magazine, note);
        System.out.println(test.solve());
    }
}
class RansomSolution{
    private HashMap<String, Integer> magazineMap;
    private HashMap<String, Integer> noteMap;

    public RansomSolution(String magazine,String ransom){
        magazineMap = new HashMap<String,Integer>();
        noteMap = new HashMap<String,Integer>();
        
        for (String var : magazine.split(" ")) {
            if( magazineMap.containsKey(var)){
                magazineMap.put(var, magazineMap.get(var)+1);
            }
            else{
                magazineMap.put(var, 1);
            }
        }

        for( String word : ransom.split(" ") ){
            if( noteMap.containsKey(word)){
                noteMap.put(word, noteMap.get(word)+1);
            }
            else{
                noteMap.put(word, 1);
            }
        }
    }

    public boolean solve(){
        for( String ransomWord : noteMap.keySet()){
            if( !magazineMap.containsKey(ransomWord)){
                return false;
            }
            else{
                if( noteMap.get(ransomWord) > magazineMap.get(ransomWord)){
                    return false;
                }
            }
        }
        return true;
    }
}