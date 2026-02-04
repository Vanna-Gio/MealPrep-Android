package com.sovanna.mealprep

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var authRepository: AuthRepository
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        authRepository = AuthRepository()

        // Check if user already logged in
        if (authRepository.isUserLoggedIn()) {
            Toast.makeText(this, "Already logged in!", Toast.LENGTH_SHORT).show()
            // TODO: Navigate to home screen (we'll add this later)
            return
        }

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<Button>(R.id.registerButton)

        loginButton.setOnClickListener {
            handleLogin()
        }

        registerButton.setOnClickListener {
            handleRegister()
        }
        val seedDataButton = findViewById<Button>(R.id.seedDataButton)
        seedDataButton.setOnClickListener {
            Toast.makeText(this, "Seeding recipes...", Toast.LENGTH_SHORT).show()
            DatabaseSeeder().seedRecipes {
                Toast.makeText(this, "5 recipes added to database!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleLogin() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        authRepository.login(email, password,
            onSuccess = {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                // TODO: Navigate to home screen
            },
            onFailure = { error ->
                Toast.makeText(this, "Login failed: $error", Toast.LENGTH_LONG).show()
            }
        )
    }

    private fun handleRegister() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }

        // Use email prefix as username for simplicity
        val username = email.substringBefore("@")

        authRepository.register(email, password, username,
            onSuccess = {
                Toast.makeText(this, "Registration successful! Please login", Toast.LENGTH_SHORT).show()
                emailEditText.text.clear()
                passwordEditText.text.clear()
            },
            onFailure = { error ->
                Toast.makeText(this, "Registration failed: $error", Toast.LENGTH_LONG).show()
            }
        )
    }
}