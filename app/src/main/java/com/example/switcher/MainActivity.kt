package com.example.switcher

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import com.example.switcher.ui.theme.LanguageSwitcherTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LanguageSwitcherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        Switcher(
                            modifier = Modifier.align(Alignment.Center),
                            switch = { languageCode ->
                                AppCompatDelegate.setApplicationLocales(
                                    LocaleListCompat.forLanguageTags(
                                        languageCode
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Switcher(modifier: Modifier = Modifier, switch: (String) -> Unit) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(R.string.welcome))

        Spacer(Modifier.height(10.dp))

        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = { switch("en-GB") }) {
            Text(text = "English")
        }
        OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = { switch("nl-NL") }) {
            Text(text = "Dutch")
        }

        Text(
            stringResource(
                R.string.language_setting,
                ConfigurationCompat.getLocales(LocalConfiguration.current)[0]?.language ?: "unknown"
            )
        )


        Spacer(Modifier.height(10.dp))

        Text(
            buildAnnotatedString {
                append(stringResource(R.string.privacy_policy_agree_request))
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append(stringResource(R.string.privacy_policy))
                }
                append(".")
            }
        )

        Spacer(Modifier.height(10.dp))

        Button(onClick = { /*TODO*/ }) {
            Text(stringResource(R.string.next).uppercase())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LanguageSwitcherTheme {
        Switcher(switch = {})
    }
}