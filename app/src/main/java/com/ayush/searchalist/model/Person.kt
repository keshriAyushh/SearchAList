package com.ayush.playground.model

data class Person(
    val firstName: String,
    val lastName: String
) {
    companion object {
        fun dummy() = listOf(
            Person("Alice", "Smith"),
            Person("Bob", "Jones"),
            Person("Charlie", "Williams"),
            Person("David", "Brown"),
            Person("Eve", "Davis"),
            Person("Frank", "Miller"),
            Person("Grace", "Wilson"),
            Person("Henry", "Moore"),
            Person("Ivy", "Taylor"),
            Person("Jack", "Anderson"),
            Person("Kate", "Thomas"),
            Person("Liam", "Jackson"),
            Person("Mia", "White"),
            Person("Noah", "Harris"),
            Person("Olivia", "Martin"),
            Person("Peter", "Thompson"),
            Person("Quinn", "Garcia"),
            Person("Ryan", "Martinez"),
            Person("Sophia", "Robinson"),
            Person("Tom", "Clark"),
            Person("Ava", "Rodriguez"),
            Person("Ben", "Lewis"),
            Person("Chloe", "Lee"),
            Person("Daniel", "Walker"),
            Person("Ella", "Hall"),
            Person("Finn", "Allen"),
            Person("Gina", "Young"),
            Person("Harry", "Hernandez"),
            Person("Isla", "King"),
            Person("Jake", "Wright"),
            Person("Lily", "Lopez"),
            Person("Max", "Hill"),
            Person("Nora", "Scott"),
            Person("Owen", "Green"),
            Person("Penny", "Adams"),
            Person("Quincy", "Baker"),
            Person("Riley", "Gonzalez"),
            Person("Stella", "Nelson"),
            Person("Theo", "Carter"),
            Person("Uma", "Mitchell"),
            Person("Victor", "Perez"),
            Person("Willow", "Roberts"),
            Person("Xander", "Turner"),
            Person("Yara", "Phillips"),
            Person("Zane", "Campbell"),
            Person("Aurora", "Parker"),
            Person("Caleb", "Evans"),
            Person("Daisy", "Edwards"),
            Person("Ethan", "Collins")
        )
    }
}
