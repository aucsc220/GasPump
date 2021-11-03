package ca.ualberta.rosanna.gaspump;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private double REG_COST_P_L = 139.9;
    private double MIDGRADE_COST_P_L = 144.9;
    private double PREMIUM_COST_P_L = 149.9;
    private double costPerLitre = 0.0; //Nothing selected
    private int numLitres = 0; //Nothing pumped

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Sets up the instance fields for regular gas.
     * @param regButton - the button the customer pressed
     * Side-effect: changes an instance variable
     */
    private void regGas(View regButton){
        costPerLitre = REG_COST_P_L;
        //TODO Grey out the other two kinds of gas
    }//regGas

    /**
     * Sets up the instance fields for mid-grade gas.
     * @param midGradeButton - the button the customer pressed
     * Side-effect: changes an instance variable
     */
    private void midGradeGas(View midGradeButton){
        costPerLitre = MIDGRADE_COST_P_L;
        //TODO Grey out the other two kinds of gas
    }//midGradeGas

    /**
     * Sets up the instance fields for premium gas.
     * @param premiumButton - the button the customer pressed
     * Side-effect: changes an instance variable
     */
    private void premiumGas(View premiumButton){
        costPerLitre = PREMIUM_COST_P_L;
        //TODO Grey out the other two kinds of gas
    }//midGradeGas
}