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

    private boolean gasTypeSelected = false; //Start with no gas type selected
    private double costPerLitre = 00.0; //Nothing selected yet so don't know cost at start
    private double progressOfFill = 0; //Used to track progress when gas type not yet selected

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set up the seek bar that handles filling the gas tank
        //
        SeekBar fillTankSB = (SeekBar) findViewById(R.id.tankFillingSeekBar);
        fillTankSB.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressOfFill = progress / 10; //Move to instance variable, to track
                        if (gasTypeSelected) {
                            TextView actLitTV = (TextView) findViewById(R.id.actLitresTV);
                            TextView actCostTV = (TextView) findViewById(R.id.actCostTV);

                            actLitTV.setText(String.format("%6.1f", progressOfFill));
                            actCostTV.setText(String.format("%6.2f",
                                    (progressOfFill * costPerLitre / 100.0)));
                        }
                        else {
                            //empty for now
                        }

                    }//onProgressChanged

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        if (!gasTypeSelected){ //Put toast here so it disappears faster
                            Toast.makeText(MainActivity.this, "Pick a type of gas",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        //empty on purpose
                    }
                }
        );//setOnSeekBarChangeListener
    }//onCreate

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

        costPerLitre = Double.parseDouble(getResources().getString(R.string.regular_price));
        gasTypeSelected = true;

        //update price
        TextView numLitresTV = (TextView) findViewById(R.id.actLitresTV);
        TextView actCostTV = (TextView) findViewById(R.id.actCostTV);
        numLitresTV.setText(String.format("%6.1f", progressOfFill));
        actCostTV.setText(String.format("%6.2f",
                (progressOfFill * costPerLitre / 100.0)));
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

        costPerLitre = Double.parseDouble(getResources().getString(R.string.mid_grad_price));
        gasTypeSelected = true;

        //update price
        TextView numLitresTV = (TextView) findViewById(R.id.actLitresTV);
        TextView actCostTV = (TextView) findViewById(R.id.actCostTV);
        numLitresTV.setText(String.format("%6.1f", progressOfFill));
        actCostTV.setText(String.format("%6.2f",
                (progressOfFill * costPerLitre / 100.0)));
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

        costPerLitre = Double.parseDouble(getResources().getString(R.string.premium_price));
        gasTypeSelected = true;

        //update price
        TextView numLitresTV = (TextView) findViewById(R.id.actLitresTV);
        TextView actCostTV = (TextView) findViewById(R.id.actCostTV);
        numLitresTV.setText(String.format("%6.1f", progressOfFill));
        actCostTV.setText(String.format("%6.2f",
                (progressOfFill * costPerLitre / 100.0)));
    }//midGradeGas
}