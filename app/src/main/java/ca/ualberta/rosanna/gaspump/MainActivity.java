package ca.ualberta.rosanna.gaspump;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private double REG_COST_P_L = 139.9;
    private double MIDGRADE_COST_P_L = 144.9;
    private double PREMIUM_COST_P_L = 149.9;
    private boolean gasTypeSelected = false; //Start with no gas type selected
    private double costPerLitre = 00.0; //Nothing selected yet so don't know cost at start
    private int numLitres = 0; //Nothing pumped

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up the filling up bar
        SeekBar fillTankSB = (SeekBar) findViewById(R.id.tankFillingSeekBar);
        fillTankSB.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (gasTypeSelected) {
                            TextView actLitTV = (TextView) findViewById(R.id.actLitresTV);
                            actLitTV.setText("" + progress + ".0");
                            TextView actCostTV = (TextView) findViewById(R.id.actCostTV);
                            actCostTV.setText(Double.toString(progress * costPerLitre / 100.0));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Pick a type of gas",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
    }

    /**
     * Sets up the instance fields for regular gas.
     * @param regButtonV - the button the customer pressed
     * Side-effect: changes an instance variable
     */
    public void regGas(View regButtonV){
        //The three gas types interact (future: consider radio button)
        //Find the 3 buttons
        Button regButton = (Button) regButtonV;
        Button midGradeButton = (Button) findViewById(R.id.midGradeButton);
        Button premiumButton = (Button) findViewById(R.id.premiumButton);

        regButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
        midGradeButton.setBackgroundColor(getResources().getColor(R.color.purple_200));
        premiumButton.setBackgroundColor(getResources().getColor(R.color.purple_200));

        costPerLitre = REG_COST_P_L;
        gasTypeSelected = true;
    }//regGas

    /**
     * Sets up the instance fields for mid-grade gas.
     * @param midGradeButtonV - the button the customer pressed
     * Side-effect: changes an instance variable
     */
    public void midGradeGas(View midGradeButtonV){
        Button midButton = (Button) midGradeButtonV;
        Button regButton = (Button) findViewById(R.id.regularButton);
        Button premiumButton = (Button) findViewById(R.id.premiumButton);

        midButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
        regButton.setBackgroundColor(getResources().getColor(R.color.purple_200));
        premiumButton.setBackgroundColor(getResources().getColor(R.color.purple_200));

        costPerLitre = MIDGRADE_COST_P_L;
        gasTypeSelected = true;
    }//midGradeGas

    /**
     * Sets up the instance fields for premium gas.
     * @param premiumButtonV - the button the customer pressed
     * Side-effect: changes an instance variable
     */
    public void premiumGas(View premiumButtonV){
        Button premiumButton = (Button) premiumButtonV;
        Button regButton = (Button) findViewById(R.id.regularButton);
        Button midGradeButton = (Button) findViewById(R.id.midGradeButton);

        premiumButton.setBackgroundColor(getResources().getColor(R.color.purple_500));
        regButton.setBackgroundColor(getResources().getColor(R.color.purple_200));
        midGradeButton.setBackgroundColor(getResources().getColor(R.color.purple_200));

        costPerLitre = PREMIUM_COST_P_L;
        gasTypeSelected = true;
    }//midGradeGas
}