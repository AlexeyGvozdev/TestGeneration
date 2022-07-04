package com.sinx.generationtest

import com.sinx.generationtest.Operation.*

class Counter(private var counter: Int = 0) {

    fun change(operation: Operation) : String {
        val oldValue = counter.toString()
        return when (operation) {
            PLUS_ONE -> ++counter
            MINUS_ONE -> --counter
        }.toString().also { newValue ->
            createTest(operation, oldValue, counter.toString())
        }
    }
}

enum class Operation {
    PLUS_ONE,
    MINUS_ONE,
}