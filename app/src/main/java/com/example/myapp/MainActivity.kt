package com.example.myapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView // Assuming you still might have a TextView for navigation
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.flFragment, FirstFragment())
            transaction.commit()
        }
    }
    fun navigateTo(fragment: Fragment) {
        val fragmentTag = fragment::class.java.name // Use the class name as a unique identifier

        // This is the key logic:
        // It pops the back stack up to (and including) the last time this fragment was added.
        // This removes any duplicates and fragments on top of it.
        val popped = supportFragmentManager.popBackStackImmediate(fragmentTag,
            FragmentManager.POP_BACK_STACK_INCLUSIVE)

        // We add a new instance only if it wasn't already on the stack.
        // If 'popped' is true, it means we found and removed the old one.
        // Now, we add the fresh instance. If you want to restore the old instance,
        // you can change this logic, but adding a new one is often cleaner.
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment, fragment, fragmentTag)
        transaction.addToBackStack(fragmentTag) // Add the new transaction to the back stack with the tag
        transaction.setReorderingAllowed(true) // Recommended for optimized transitions
        transaction.commit()
    }
    }
