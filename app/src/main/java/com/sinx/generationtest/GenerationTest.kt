package com.sinx.generationtest

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


val typeSpec = TypeSpec.classBuilder("NameType")//.superclass(ClassName("generationtest", "CounterTest"))
val fileSpec = FileSpec.builder("com.sinx.generationtest", "NameFile")
    .addImport("org.junit.Assert", "assertEquals")
    .addImport("com.sinx.generationtest", "Counter")
    .addImport(Operation.MINUS_ONE)
    .addImport(Operation.PLUS_ONE)

fun createTest(operation: Operation, oldValue: String, newValue: String) {
    val nameOperation = when (operation) {
        Operation.PLUS_ONE -> "+"
        Operation.MINUS_ONE -> "-"
    }
    val main = FunSpec.builder("case_${oldValue}_${operation}_equals_${newValue}")
        .addCode(
            """
                |val oldValue = $oldValue
                |val operation = $operation
                |val counter = Counter(oldValue)
                |val targetValue = "$newValue"
                |assertEquals(targetValue, counter.change(operation))
        """.trimMargin()
        )
        .addModifiers(KModifier.FUN)
        .addKdoc("$oldValue $nameOperation 1 = $newValue", "asd")
        .addAnnotation(ClassName("org.junit", "Test"))
        .build()
    typeSpec.addFunction(main)
}

fun closeTest(dir: String) {
    val file = File(dir)
    file.createNewFile()
    fileSpec.addType(typeSpec.build()).build().writeTo(file)
}