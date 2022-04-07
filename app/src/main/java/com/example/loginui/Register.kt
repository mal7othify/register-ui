package com.example.loginui

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.loginui.Validators.isEmailValid
import com.example.loginui.Validators.isPassValid
import com.example.loginui.Validators.isPasswordMatching
import com.example.loginui.Validators.isValidArabicName

@Composable
fun Register() {
  val context = LocalContext.current
  var userFullName by remember { mutableStateOf("") }
  var userEmail by remember { mutableStateOf("") }
  var userPassword by remember { mutableStateOf("") }
  var comparePassword by remember { mutableStateOf("") }
  var passwordVisible by rememberSaveable { mutableStateOf(false) }

  val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
  val description = if (passwordVisible) "Hide password" else "Show password"

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(24.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = "Welcome!",
      style = MaterialTheme.typography.h3,
      modifier = Modifier.padding(vertical = 16.dp)
    )

    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(), value = userFullName, label = {
        Text(text = "Full Name")
      }, onValueChange = {
        userFullName = it
      },
      singleLine = true,
      leadingIcon = {
        Icon(
          imageVector = Icons.Default.AccountCircle,
          contentDescription = ""
        )
      },
      isError = !userFullName.isValidArabicName()
    )
    AnimatedVisibility(visible = !userFullName.isValidArabicName()) {
      Text(
        text = "Invalid Arabic name",
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
      )
    }

    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      value = userEmail,
      label = {
        Text(text = "Email")
      }, onValueChange = {
        userEmail = it
      },
      singleLine = true,
      leadingIcon = {
        Icon(
          imageVector = Icons.Default.Email,
          contentDescription = ""
        )
      },
      isError = !userEmail.isEmailValid()
    )
    AnimatedVisibility(visible = !userEmail.isEmailValid()) {
      Text(
        text = "Invalid email",
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
      )
    }

    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
      value = userPassword,
      label = {
        Text(text = "Password")
      },
      onValueChange = {
        userPassword = it
      },
      singleLine = true,
      leadingIcon = {
        Icon(
          imageVector = Icons.Default.Lock,
          contentDescription = ""
        )
      },
      trailingIcon = {
        IconButton(onClick = { passwordVisible = !passwordVisible }) {
          Icon(imageVector = image, description)
        }
      },
      isError = !userPassword.isPassValid()
    )
    AnimatedVisibility(visible = !userPassword.isPassValid()) {
      Text(
        text = "Password must contain 1 number (0-9), 1 uppercase letters, 1 lowercase letters, between 8-16 characters with no space",
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
      )
    }

    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
      value = comparePassword,
      label = {
        Text(text = "Password")
      },
      onValueChange = {
        comparePassword = it
      },
      singleLine = true,
      leadingIcon = {
        Icon(
          imageVector = Icons.Default.Lock,
          contentDescription = ""
        )
      },
      trailingIcon = {
        IconButton(onClick = { passwordVisible = !passwordVisible }) {
          Icon(imageVector = image, description)
        }
      },
      isError = !comparePassword.isPasswordMatching(userPassword)
    )
    AnimatedVisibility(visible = !comparePassword.isPasswordMatching(userPassword)) {
      Text(
        text = "Passwords not matching",
        color = MaterialTheme.colors.error,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
      )
    }

    // Gender?

    Button(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 16.dp)
        .height(50.dp),
      enabled = isValid(userFullName, userEmail, userPassword, comparePassword),
      content = {
        Text(text = "Login")
      },
      onClick = {
        Toast.makeText(context, "Registered successfully", Toast.LENGTH_LONG).show()
      }
    )
//    AnimatedVisibility(visible = !isValid(userFullName, userEmail, userPassword, comparePassword)) {
//      Text(
//        text = "Make sure you fill the fields with the correct values",
//        color = MaterialTheme.colors.error,
//        textAlign = TextAlign.Start,
//        modifier = Modifier.fillMaxWidth()
//      )
//    }
  }
}

fun isValid(name: String, email: String, password: String, comparePass: String): Boolean {
  return name.isValidArabicName() && email.isEmailValid() && password.isPassValid() && comparePass.isPasswordMatching(
    password
  )
}
