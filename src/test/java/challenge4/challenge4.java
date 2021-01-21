package challenge4;

import com.ibm.icu.number.NumberFormatter;
import com.ibm.icu.text.NumberFormat;
import com.ibm.icu.text.RuleBasedNumberFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class challenge4 {

    public List<Integer> fibonacciSequencer(int n){
        List<Integer> sequence = new ArrayList<>();
        int n1 = 0, n2 = 1;
        for(int i = 0; i <= (n-3); i++){
            int sum = n1 + n2;
            sequence.add(sum);
            n1 = n2;
            n2 = sum;
        }
        return sequence;
    }

    public void printFibonacci(int length){
        List<Integer> list = fibonacciSequencer(length);
        int num = list.get(list.size()-1);
        NumberFormat formatter = new RuleBasedNumberFormat(RuleBasedNumberFormat.SPELLOUT);
        System.out.println(num + " - " + formatter.format(num));
    }

    @Test()
    public void fibonacci() throws Exception{
        printFibonacci(8);
        printFibonacci(13);
        printFibonacci(22);
    }

}
