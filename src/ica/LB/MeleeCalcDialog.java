package ica.LB;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;


/**
 * Created by jcapuano on 6/28/2014.
 */
public class MeleeCalcDialog extends DialogFragment {

    public interface OnMeleeCalcFinishListener {
        public abstract void onFinishDialog(Double value);
    }
    

	private Button btnMeleeIncrPrev;
	private Button btnMeleeIncrNext;
	private EditText editMeleeIncrValue;
	
	private Button btnMeleeLossPrev;
	private Button btnMeleeLossNext;
	private EditText editMeleeLossValue;
	
	private Button btnMeleeValuePrev;
	private Button btnMeleeValueNext;
	private EditText editMeleeValueValue;
	
	private Button btnMeleeLancePrev;
	private Button btnMeleeLanceNext;
	private EditText editMeleeLanceValue;
	
	private Button btnMeleeTotalPrev;
	private Button btnMeleeTotalNext;
	private EditText editMeleeTotalValue;
	
	private ToggleButton btnMeleeMods13;
	private ToggleButton btnMeleeMods12;
	private ToggleButton btnMeleeMods32;
	private ToggleButton btnMeleeMods2;
	private ToggleButton btnMeleeModsLnc;
	
    private OnMeleeCalcFinishListener evFinishListener;
    
    private double total;
    
    public MeleeCalcDialog() {
        // Empty constructor required for DialogFragment
    }

    public void setFinishListener(OnMeleeCalcFinishListener l) {
        evFinishListener = l;
    }
    
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        total = 0.0;
    
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.meleecalc, null);    
        
		btnMeleeIncrPrev	 = (Button)view.findViewById(R.id.btnMeleeIncrPrev	);
		btnMeleeIncrNext	 = (Button)view.findViewById(R.id.btnMeleeIncrNext	);
		editMeleeIncrValue	 = (EditText)view.findViewById(R.id.editMeleeIncrValue);
						
		btnMeleeLossPrev	 = (Button)view.findViewById(R.id.btnMeleeLossPrev	);
		btnMeleeLossNext	 = (Button)view.findViewById(R.id.btnMeleeLossNext	);
		editMeleeLossValue	 = (EditText)view.findViewById(R.id.editMeleeLossValue);
	
		btnMeleeValuePrev	 = (Button)view.findViewById(R.id.btnMeleeValuePrev	);
		btnMeleeValueNext	 = (Button)view.findViewById(R.id.btnMeleeValueNext	);
		editMeleeValueValue	 = (EditText)view.findViewById(R.id.editMeleeValueValue);
	
		btnMeleeLancePrev	 = (Button)view.findViewById(R.id.btnMeleeLancePrev	);
		btnMeleeLanceNext	 = (Button)view.findViewById(R.id.btnMeleeLanceNext	);
		editMeleeLanceValue	 = (EditText)view.findViewById(R.id.editMeleeLanceValue);
	
		btnMeleeTotalPrev	 = (Button)view.findViewById(R.id.btnMeleeTotalPrev	);
		btnMeleeTotalNext	 = (Button)view.findViewById(R.id.btnMeleeTotalNext	);
		editMeleeTotalValue	 = (EditText)view.findViewById(R.id.editMeleeTotalValue);
	
		btnMeleeMods13  = (ToggleButton)view.findViewById(R.id.btnMeleeMods13 );
		btnMeleeMods12  = (ToggleButton)view.findViewById(R.id.btnMeleeMods12 );
		btnMeleeMods32  = (ToggleButton)view.findViewById(R.id.btnMeleeMods32 );
		btnMeleeMods2   = (ToggleButton)view.findViewById(R.id.btnMeleeMods2  );
		btnMeleeModsLnc = (ToggleButton)view.findViewById(R.id.btnMeleeModsLnc);
	
		editMeleeIncrValue.setText("1");
		editMeleeLossValue.setText("0");
		editMeleeValueValue.setText("1");
		editMeleeLanceValue.setText("0");
		editMeleeTotalValue.setText("1");
        
		btnMeleeIncrPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeIncrements();
			    if (--value < 1) value = 1;
			    editMeleeIncrValue.setText(Integer.toString(value));
                updateUnit();
			}
		});
		btnMeleeIncrNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int value = getMeleeIncrements();
                editMeleeIncrValue.setText(Integer.toString(++value));
                updateUnit();
            }
        });
		editMeleeIncrValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }

            public void afterTextChanged(Editable s) {
                updateUnit();
            }
        });
	
		btnMeleeLossPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeLoss();
			    if (--value < 0) value = 0;
			    editMeleeLossValue.setText(Integer.toString(value));
                updateUnit();
			}
		});
		btnMeleeLossNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int value = getMeleeLoss();
                editMeleeLossValue.setText(Integer.toString(++value));
                updateUnit();
            }
        });
		editMeleeLossValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }

            public void afterTextChanged(Editable s) {
                updateUnit();
            }
        });
	
		btnMeleeValuePrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeValue();
			    if (--value < 1) value = 1;
			    editMeleeValueValue.setText(Integer.toString(value));
                updateUnit();
			}
		});
		btnMeleeValueNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int value = getMeleeValue();
                editMeleeValueValue.setText(Integer.toString(++value));
                updateUnit();
            }
        });
		editMeleeValueValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }

            public void afterTextChanged(Editable s) {
                updateUnit();
            }
        });
	
		btnMeleeLancePrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    int value = getMeleeLance();
			    if (--value < 0) value = 0;
			    editMeleeLanceValue.setText(Integer.toString(value));
                updateUnit();
			}
		});
		btnMeleeLanceNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                int value = getMeleeLance();
                editMeleeLanceValue.setText(Integer.toString(++value));
                updateUnit();
            }
        });
		editMeleeLanceValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }

            public void afterTextChanged(Editable s) {
                updateUnit();
            }
        });
	
		btnMeleeTotalPrev.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getMeleeTotal();
			    if (--value < 1) value = 1;
			    editMeleeTotalValue.setText(Double.toString(value));
			}
		});
		btnMeleeTotalNext.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    double value = getMeleeTotal();
			    editMeleeTotalValue.setText(Double.toString(++value));
			}
		});
        /*
		editMeleeTotalValue.addTextChangedListener(new TextWatcher() {
			@Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
			@Override
            public void onTextChanged(CharSequence s, int start, int before, int end) {
            }
            public void afterTextChanged(Editable s) {
            }
        });
		*/
	
		btnMeleeMods13.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    updateUnit();
			}
		});
		btnMeleeMods12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    updateUnit();
			}
		});
		btnMeleeMods32.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    updateUnit();
			}
		});
		btnMeleeMods2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			    updateUnit();
			}
		});
	
		btnMeleeModsLnc.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                updateUnit();
            }
        });

        
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
            .setView(view)
            .setTitle("Melee Calculator")
            .setCancelable(false)
            .setNeutralButton("Add", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    /*
                    total += getMeleeTotal();
                    */
                }
            })
            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    /*
                    if (evFinishListener != null) {
                        if (total <= 0) total = getMeleeTotal();

                        evFinishListener.onFinishDialog(total);
                    }
                    dialog.dismiss();
                    */
                }
            })
            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int id) {
                    /*
                    if (evFinishListener != null) {
                        evFinishListener.onFinishDialog(0.0);
                    }
                    dialog.cancel();
                    */
                }
            });
        return builder.create();
    }
    
    @Override
    public void onStart() {
        super.onStart();    //super.onStart() is where dialog.show() is actually called on the underlying dialog, so we have to do it after this point
        AlertDialog d = (AlertDialog)getDialog();
        if(d != null) {
            Button positiveButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            positiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (evFinishListener != null) {
                        if (total <= 0) total = getMeleeTotal();
                        evFinishListener.onFinishDialog(total);
                    }
                    dismiss();
                }
            });
            
            Button neutralButton = (Button) d.getButton(Dialog.BUTTON_NEUTRAL);
            neutralButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    total += getMeleeTotal();
                }
            });
            
            Button negativeButton = (Button) d.getButton(Dialog.BUTTON_NEGATIVE);
            negativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (evFinishListener != null) {
                        evFinishListener.onFinishDialog(0.0);
                    }
                    dismiss();
                }
            });
            
        }
    }    

	int getMeleeIncrements() {
        String v = editMeleeIncrValue.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;
	}
	int getMeleeLoss() {
        String v = editMeleeLossValue.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;
	}
	int getMeleeValue() {
        String v = editMeleeValueValue.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;
	}
	int getMeleeLance() {
        String v = editMeleeLanceValue.getText().toString();
        if (!v.isEmpty())
            return Integer.parseInt(v);
        return 1;
	}
	double getMeleeTotal() {
        String v = editMeleeTotalValue.getText().toString();
        if (!v.isEmpty())
            return Double.parseDouble(v);
        return 1;           
	}
    
	void updateUnit() {
        int incr = getMeleeIncrements();
	    int loss = getMeleeLoss();
		double melee = getMeleeValue();
		double lance = getMeleeLance();
        
		double val = melee * ((double)(incr - loss) / (double)incr);
		if (btnMeleeMods13.isChecked()) 
	        val /= 3.0;
		if (btnMeleeMods12.isChecked()) 
	        val /= 2.0;
		if (btnMeleeMods32.isChecked()) 
	        val *= 1.5;
		if (btnMeleeMods2.isChecked()) 
	        val *= 2.0;
        
		if (btnMeleeModsLnc.isChecked()) 
            lance *= 2.0;
        
		editMeleeTotalValue.setText(Double.toString(val + lance));
	}
}