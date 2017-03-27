package com.example.a2b;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button b1, b2;
	TextView tv1, tv2, tv3, tv6;
	EditText et;
	String answer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findview();
		answer = creatanswer(3);
	}

	void findview() {
		b1 = (Button) findViewById(R.id.button1);
		b2 = (Button) findViewById(R.id.button2);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv3 = (TextView) findViewById(R.id.textView3);
		tv2 = (TextView) findViewById(R.id.textView2);
		tv6 = (TextView) findViewById(R.id.textView6);
		et = (EditText) findViewById(R.id.editText1);
		b1.setOnClickListener(c1);
		b2.setOnClickListener(c2);
	}

	// 第二個button的監聽器 放棄
	OnClickListener c1 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			tv3.setText(answer);
		}
	};

	// 第一個button的監聽器 給你猜
	OnClickListener c2 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			guess();
		}
	};

	// 猜
	void guess() {
		String strGuess = et.getText().toString();
		String result = result(strGuess);
		tv6.append("\n" + strGuess + " => " + result + "\n");
		et.setText("");

	}

	// 創造1A2B的解答
	String creatanswer(int a) {
		int number[] = new int[a];
		int temp;
		boolean check;
		for (int i = 0; i < number.length; i++) {
			do {
				check = false;
				temp = (int) (Math.random() * 9) + 1;
				for (int j = 0; j < i; j++) {
					if (temp == number[j]) {
						check = true;
					}
				}
			} while (check);
			number[i] = temp;
		}
		String s = "";
		for (int v : number) {
			s += v;
		}
		return s;
	}

	// 產生幾A幾B
	String result(String ss) {
		int A, B;
		A = B = 0;
		if (ss.matches("^[0-9]{3}$")) {

			for (int i = 0; i < ss.length(); i++) {

				if (answer.charAt(i) == ss.charAt(i)) {

					A++;
				} else if (answer.indexOf(ss.charAt(i)) != -1) {
					B++;
				}
			}

		}
		return A + "A" + B + "B";
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
