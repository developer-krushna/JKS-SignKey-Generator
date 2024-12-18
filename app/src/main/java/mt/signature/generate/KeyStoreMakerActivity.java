/*
 * JKS-SignKey-Generator , Android jks key generator with optional certificate details
 * Copyright 2024, developer-krushna
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of developer-krushna nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 
 
 *     Please contact Krushna by email mt.modder.hub@gmail.com if you need
 *     additional information or have any questions
 */
 
package mt.signature.generate;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.sun.security.*;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.textfield.*;
import java.io.*;
import java.text.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import android.util.Base64;
import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import android.sun.security.x509.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.CodeSource;
import java.util.*;


/*
Author @developer-krushna on 19-Dec-2024
*/

public class KeyStoreMakerActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private String pk8 = "";
	private String pem = "";
	
	private RelativeLayout linear1;
	private LinearLayout linear12;
	private LinearLayout fab;
	private ScrollView vscroll3;
	private LinearLayout linear2;
	private LinearLayout linear13;
	private TextInputLayout textinputlayout1;
	private TextInputLayout textinput_keyName;
	private TextInputLayout textinput2;
	private TextInputLayout textinput3;
	private TextInputLayout textinput4;
	private TextInputLayout textinput5;
	private TextInputLayout textinput6;
	private Switch moreOption;
	private LinearLayout linear_more;
	private TextView copyright;
	private CheckBox generatePairKeys;
	private CheckBox generateJKS;
	private EditText directory;
	private EditText key_name;
	private EditText storePass;
	private EditText keyPass;
	private EditText keySize;
	private EditText date;
	private EditText commonName;
	private TextInputLayout textinput7;
	private TextInputLayout textinput8;
	private TextInputLayout textinput9;
	private TextInputLayout textinput10;
	private TextInputLayout textinput11;
	private EditText organizationUnit;
	private EditText organizationName;
	private EditText localityName;
	private EditText stateName;
	private EditText country;
	private ImageView fab_icon;
	private TextView fab_text;
	
	private SharedPreferences s;
	private AlertDialog.Builder d;
	private TimerTask t;
	private ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.key_store_maker);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		linear1 = findViewById(R.id.linear1);
		linear12 = findViewById(R.id.linear12);
		fab = findViewById(R.id.fab);
		vscroll3 = findViewById(R.id.vscroll3);
		linear2 = findViewById(R.id.linear2);
		linear13 = findViewById(R.id.linear13);
		textinputlayout1 = findViewById(R.id.textinputlayout1);
		textinput_keyName = findViewById(R.id.textinput_keyName);
		textinput2 = findViewById(R.id.textinput2);
		textinput3 = findViewById(R.id.textinput3);
		textinput4 = findViewById(R.id.textinput4);
		textinput5 = findViewById(R.id.textinput5);
		textinput6 = findViewById(R.id.textinput6);
		moreOption = findViewById(R.id.switch1);
		linear_more = findViewById(R.id.linear_more);
		copyright = findViewById(R.id.copyright);
		generatePairKeys = findViewById(R.id.checkbox1);
		generateJKS = findViewById(R.id.checkbox2);
		directory = findViewById(R.id.directory);
		key_name = findViewById(R.id.key_name);
		storePass = findViewById(R.id.storePass);
		keyPass = findViewById(R.id.keyPass);
		keySize = findViewById(R.id.keySize);
		date = findViewById(R.id.date);
		commonName = findViewById(R.id.commonName);
		textinput7 = findViewById(R.id.textinput7);
		textinput8 = findViewById(R.id.textinput8);
		textinput9 = findViewById(R.id.textinput9);
		textinput10 = findViewById(R.id.textinput10);
		textinput11 = findViewById(R.id.textinput11);
		organizationUnit = findViewById(R.id.organizationUnit);
		organizationName = findViewById(R.id.organizationName);
		localityName = findViewById(R.id.localityName);
		stateName = findViewById(R.id.stateName);
		country = findViewById(R.id.country);
		fab_icon = findViewById(R.id.fab_icon);
		fab_text = findViewById(R.id.fab_text);
		s = getSharedPreferences("KeyStore", Activity.MODE_PRIVATE);
		d = new AlertDialog.Builder(this);
		
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// Validate input fields
				if (generatePairKeys.isChecked() || generateJKS.isChecked()) {
					if (key_name.getText().toString().isEmpty()) {
						textinput_keyName.setError("Error");
					} else if (keySize.getText().toString().isEmpty()) {
						textinput4.setError("Error");
					} else if (date.getText().toString().isEmpty()) {
						textinput5.setError("Error");
					} else if (commonName.getText().toString().isEmpty()) {
						textinput6.setError("Error");
					} else {
						// Check if jks generate checked
						if (generateJKS.isChecked()) {
							if (storePass.getText().toString().isEmpty()) {
								textinput2.setError("Error");
								return;
							}
							if (keyPass.getText().toString().isEmpty()) {
								textinput3.setError("Error");
								return;
							}
						}
						// Start processing with progress dialog
						progress = new ProgressDialog(KeyStoreMakerActivity.this);
						progress.setMessage("Generating...");
						progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
						progress.show();
						
						// Run the background task on a separate thread
						new Thread(new Runnable() {
							@Override
							public void run() {
								try {
									save();
									// On success, show success dialog on the UI thread
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											_SuccessfulDialog();
											progress.dismiss();
										}
									});
								} catch (Exception e) {
									// On error, show error dialog on the UI thread
									runOnUiThread(new Runnable() {
										@Override
										public void run() {
											_Dlg("Error", e.toString());
											progress.dismiss();
										}
									});
								}
							}
						}).start();
					}
				} else {
					SketchwareUtil.showMessage(getApplicationContext(), "Please select any key method");
				}
			}
		});
		
		
		moreOption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					linear_more.setVisibility(View.VISIBLE);
					country.setText(KeyStoreMakerActivity.this.getResources().getConfiguration().locale.getCountry());
				} else {
					linear_more.setVisibility(View.GONE);
					country.setText("");
					organizationName.setText("");
					organizationUnit.setText("");
					localityName.setText("");
					stateName.setText("");
				}
			}
		});
		
		generateJKS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					textinput2.setVisibility(View.VISIBLE);
					textinput3.setVisibility(View.VISIBLE);
				} else {
					textinput2.setVisibility(View.GONE);
					textinput3.setVisibility(View.GONE);
				}
			}
		});
		
		// TestWatcher for disabling blank input error
		addTextWatcher(key_name, textinput_keyName);
		addTextWatcher(storePass, textinput2);
		addTextWatcher(keyPass, textinput3);
		addTextWatcher(keySize, textinput4);
		addTextWatcher(date, textinput5);
		addTextWatcher(commonName, textinput6);
	}
	
	private void addTextWatcher(final EditText editText, final TextInputLayout textInputLayout) {
		editText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (textInputLayout != null) {
					textInputLayout.setErrorEnabled(false);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				// Optional: Can be left empty
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				// Optional: Can be left empty
			}
		});
	}
	
	private void initializeLogic() {
		setTitle("Signature Key Generator");
		fab_icon.setImageResource(R.drawable.ic_build_mt);
		_GradientDrawable(fab, 80, 0, 15, "#09A7EC", "#09A7EC", true, true, 10);
	}
	
	public static class KeyParam {
		public String certOrAlias;
		public String alias;
		public String jksPath;
		public String commonName;
		public String country;
		public long days;
		public String keyPass;
		public String keyPath;
		public int keySize;
		public String localityName;
		public String organizationName;
		public String organizationUnit;
		public String stateName;
		public String storePass;
		
	}
	private KeyParam save() {
		// Initialize key parameters
		KeyParam keyParam = new KeyParam();
		
		// Retrieve key size from input field, default to 2048 if empty
		String obj = this.keySize.getText().toString();
		keyParam.keySize = obj.isEmpty() ? 2048 : Integer.parseInt(obj);
		
		// Generate file paths for key and certificate
		String basePath = Environment.getExternalStorageDirectory() + directory.getText().toString() + this.key_name.getText().toString();
		keyParam.keyPath = basePath + ".pk8";
		keyParam.certOrAlias = basePath + ".x509.pem";
		keyParam.alias = key_name.getText().toString();
		keyParam.jksPath = basePath + ".jks";
		
		// Retrieve other fields
		keyParam.storePass = this.storePass.getText().toString();
		keyParam.keyPass = this.keyPass.getText().toString();
		keyParam.commonName = this.commonName.getText().toString();
		keyParam.organizationUnit = this.organizationUnit.getText().toString();
		keyParam.organizationName = this.organizationName.getText().toString();
		keyParam.localityName = this.localityName.getText().toString();
		keyParam.stateName = this.stateName.getText().toString();
		keyParam.country = this.country.getText().toString();
		keyParam.days = Long.parseLong(this.date.getText().toString()) * 365;
		
		try {
			generateKey(keyParam);
			Toast.makeText(getApplicationContext(), "Success..", 0).show();
			return keyParam;
		} catch (Exception e) {
			StringBuilder certificateEncodingException = new StringBuilder(e.getMessage());
			for (StackTraceElement element : e.getStackTrace()) {
				certificateEncodingException.append('\n').append(element);
			}
			_Dlg("Error", certificateEncodingException.toString());
			return null;
		}
	}
	private X509Certificate generateCert(PrivateKey privateKey, PublicKey publicKey, X500Name x500Name, Date date, Date date2, CertificateExtensions certificateExtensions) throws Exception {
		String str = "SHA512withRSA";
		try {
			CertificateValidity certificateValidity = new CertificateValidity(date, date2);
			X509CertInfo x509CertInfo = new X509CertInfo();
			x509CertInfo.set("version", new CertificateVersion(2));
			x509CertInfo.set("serialNumber", new CertificateSerialNumber(new Random().nextInt() & Integer.MAX_VALUE));
			x509CertInfo.set("algorithmID", new CertificateAlgorithmId(AlgorithmId.get(str)));
			x509CertInfo.set("subject", new CertificateSubjectName(x500Name));
			x509CertInfo.set("key", new CertificateX509Key(publicKey));
			x509CertInfo.set("validity", certificateValidity);
			x509CertInfo.set("issuer", new CertificateIssuerName(x500Name));
			
			// Add certificate extensions if present
			if (certificateExtensions != null) {
				x509CertInfo.set("extensions", certificateExtensions);
			}
			
			X509CertImpl x509CertImpl = new X509CertImpl(x509CertInfo);
			x509CertImpl.sign(privateKey, str);
			return x509CertImpl;
		} catch (IOException e) {
			throw new CertificateEncodingException("getSelfCert: " + e.getMessage());
		}
	}
	
	private void writeCertificate(PrivateKey privateKey, X509Certificate x509Certificate, KeyParam keyParam) throws Exception {
		// Write private key to file
		File keyFile = new File(keyParam.keyPath);
		if (!keyFile.getParentFile().exists()) {
			keyFile.getParentFile().mkdirs();
		}
		try (FileOutputStream key = new FileOutputStream(keyFile)) {
			key.write(privateKey.getEncoded());
		}
		
		// Write certificate to file in PEM format
		try (FileOutputStream cery = new FileOutputStream(keyParam.certOrAlias)) {
			cery.write("-----BEGIN CERTIFICATE-----".getBytes());
			byte[] encoded = Base64.encode(x509Certificate.getEncoded(), Base64.DEFAULT);
			cery.write(encoded);
			cery.write("-----END CERTIFICATE-----".getBytes());
			cery.flush();
		}
	}
	
	private void writeJks(PrivateKey privateKey, X509Certificate x509Certificate, KeyParam keyParam) throws Exception {
		KeyStore keyStore = KeyStore.getInstance("JKS");
		char[] storePass = keyParam.storePass.toCharArray();
		
		// Load existing KeyStore or initialize a new one
		try (FileInputStream fis = new FileInputStream(keyParam.jksPath)) {
			if (new File(keyParam.jksPath).exists()) {
				keyStore.load(fis, storePass);
			} else {
				keyStore.load(null, storePass); 
			}
		}
		
		char[] keyPass = keyParam.keyPass.toCharArray();
		keyStore.setKeyEntry(keyParam.alias, privateKey, keyPass, new Certificate[]{x509Certificate});
		
		// Store updated KeyStore
		try (FileOutputStream fos = new FileOutputStream(keyParam.jksPath)) {
			keyStore.store(fos, storePass);
		}
	}
	
	private void generateKey(KeyParam keyParam) throws Exception {
		// Generate RSA KeyPair
		KeyPairGenerator instance = KeyPairGenerator.getInstance("RSA");
		instance.initialize(keyParam.keySize, SecureRandom.getInstance("SHA1PRNG"));
		KeyPair generateKeyPair = instance.generateKeyPair();
		PublicKey publicKey = generateKeyPair.getPublic();
		PrivateKey privateKey = generateKeyPair.getPrivate();
		
		// Set certificate extensions
		CertificateExtensions certificateExtensions = new CertificateExtensions();
		certificateExtensions.set("SubjectKeyIdentifier", new SubjectKeyIdentifierExtension(new KeyIdentifier(publicKey).getIdentifier()));
		
		// Build X500Name using provided parameters
		StringBuilder x500NameBuilder = new StringBuilder("CN=").append(keyParam.commonName);
		if (keyParam.organizationName != null && !keyParam.organizationName.isEmpty()) {
			x500NameBuilder.append(", O=").append(keyParam.organizationName);
		}
		if (keyParam.organizationUnit != null && !keyParam.organizationUnit.isEmpty()) {
			x500NameBuilder.append(", OU=").append(keyParam.organizationUnit);
		}
		if (keyParam.localityName != null && !keyParam.localityName.isEmpty()) {
			x500NameBuilder.append(", L=").append(keyParam.localityName);
		}
		if (keyParam.stateName != null && !keyParam.stateName.isEmpty()) {
			x500NameBuilder.append(", ST=").append(keyParam.stateName);
		}
		if (keyParam.country != null && !keyParam.country.isEmpty()) {
			x500NameBuilder.append(", C=").append(keyParam.country);
		}
		X500Name x500Name = new X500Name(x500NameBuilder.toString());
		
		// Set validity period for the certificate
		Date date = new Date();
		long j = (keyParam.days * 24) * 3600000;
		Date date2 = new Date();
		date2.setTime(j + date.getTime());
		certificateExtensions.set("PrivateKeyUsage", new PrivateKeyUsageExtension(date, date2));
		
		// Generate certificate and save based on checkbox selection
		X509Certificate generatedCert = generateCert(privateKey, publicKey, x500Name, date, date2, certificateExtensions);
		if (generatePairKeys.isChecked()) {
			writeCertificate(privateKey, generatedCert, keyParam);
		}
		if (generateJKS.isChecked()) {
			writeJks(privateKey, generateCert(privateKey, publicKey, x500Name, date, date2, certificateExtensions), keyParam);
		}
	}
	
	
	public void _GradientDrawable(final View _view, final double _radius, final double _stroke, final double _shadow, final String _color, final String _borderColor, final boolean _ripple, final boolean _clickAnim, final double _animDuration) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9e9e9e")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		} else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			_view.setBackground(gd);
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
		}
		if (_clickAnim) {
			_view.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()){
						case MotionEvent.ACTION_DOWN:{
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues(0.9f);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues(0.9f);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							break;
						}
						case MotionEvent.ACTION_UP:{
							
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues((float)1);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues((float)1);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							
							break;
						}
					}
					return false;
				}
			});
		}
	}
	
	
	public void _Dlg(final String _title, final String _message) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				d.setTitle(_title);
				d.setMessage(_message);
				d.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				
			}
		});
	}
	
	
	public void _SuccessfulDialog() {
		runOnUiThread(new Runnable(){
			@Override
			public void run() {
				d.setTitle("Successful");
				d.setMessage("");
			}
		});
	}
	
}
