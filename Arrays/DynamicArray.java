import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'dynamicArray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. 2D_INTEGER_ARRAY queries
     */

    public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
    // Write your code here
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>(n);
        for( int i =0;i<n;i++){
            arr.add(i,new ArrayList<Integer>());
        }
        int q = queries.size();
        List<Integer> ansArray = new ArrayList<>();
        int lastAnswer = 0;
        for(List<Integer> queryParams : queries){
            int type = queryParams.get(0); 
            int x = queryParams.get(1);
            int y = queryParams.get(2);
            int idx = (x ^ lastAnswer) % n;
            if(idx < n){
                if(type == 1){
                    arr.get(idx).add(y);
                }else{
                    int temp = y % arr.get(idx).size();
                    if(temp < arr.get(idx).size()){
                        lastAnswer = arr.get(idx).get(temp);
                        ansArray.add(lastAnswer);
                    }
                }
            }

        }
        return ansArray;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> result = Result.dynamicArray(n, queries);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
