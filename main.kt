package machine

fun main() {
    val a = CoffeeMachine()
    a.askAction()
}

enum class Coffee(var coffee: String, var waterNeeded: Int, var beansNeeded: Int, var milkNeeded: Int, var cupsNeeded: Int, var price: Int) {
    ESPRESSO("espresso", 250, 16, 0, 1, 4),
    LATTE("latte", 350, 20, 75, 1, 7),
    CAPPUCCINO("cappuccino", 200, 12, 100, 1, 6)
}

class CoffeeMachine {
    var moneyHas = 550
    var waterHas = 400
    var milkHas = 540
    var beansHas = 120
    var cupsHas = 9
    var action = ""

    fun askAction(){
        if (action != "exit") {
            print("Write action (buy, fill, take, remaining, exit):")
            action = readln()
            print("\n")
            while(action != "exit") {
                when (action) {
                    "buy" -> {
                        buyCoffee()
                        askAction()
                    }
                    "fill" -> {
                        fillMachine()
                        askAction()
                    }
                    "take" -> {
                        takeMoney()
                        askAction()
                    }
                    "remaining" -> {
                        remaining()
                        askAction()
                    }
                    "exit" -> break
                }
            }
        }
    }



    fun fillMachine() {
        println("Write how many ml of water do you want to add:")
        waterHas += readln().toInt()
        println("Write how many ml of milk do you want to add:")
        milkHas += readln().toInt()
        println("Write how many grams of coffee beans do you want to add:")
        beansHas += readln().toInt()
        println("Write how many disposable cups of coffee do you want to add:")
        cupsHas += readln().toInt()
    }

    fun takeMoney() {
        println("I gave you $$moneyHas")
        moneyHas = 0
    }

    fun remaining() {
        println("The coffee machine has:\n$waterHas of water\n$milkHas of milk\n$beansHas of coffee beans\n$cupsHas of disposable cups\n$$moneyHas of money")
        print("\n")
    }

    fun buyCoffee() {
        print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
        var c = when (readln()) {
            "1" -> Coffee.ESPRESSO
            "2" -> Coffee.LATTE
            "3" -> Coffee.CAPPUCCINO
            "back" -> return
            else -> return
        }
        when {
            waterHas < c.waterNeeded -> println("Sorry, not enough water!")
            beansHas < c.beansNeeded -> println("Sorry, not enough beans!")
            milkHas < c.milkNeeded -> println("Sorry, not enough milk!")
            cupsHas < c.cupsNeeded -> println("Sorry, not enough cups!")
            else -> {
                println("I have enough resources, making you a coffee!\n")
                waterHas -= c.waterNeeded
                beansHas -= c.beansNeeded
                milkHas -= c.milkNeeded
                cupsHas -= c.cupsNeeded
                moneyHas += c.price
            }
        }

    }
}
