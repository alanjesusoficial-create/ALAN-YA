package com.example.data

import com.example.data.models.CivilisationInfo
import com.example.data.models.ClassificationCard
import com.example.data.models.QuizQuestion

object HistoricalData {

    val civilizations = listOf(
        CivilisationInfo(
            id = "mesopotamia",
            name = "Mesopotamia (Babilonia)",
            period = "c. 3000 a.C. - 500 a.C.",
            baseNumber = 60,
            systemType = "Posicional (Sexagesimal)",
            summary = "Desarrollaron el primer sistema posicional conocido en tablillas de arcilla con estiletes de caña cuneiforme. Agrupaban en base 60 utilizando únicamente dos símbolos combinados hasta el 59.",
            keySymbols = listOf(
                "T (Cuña vertical)" to "Unidad (1)",
                "< (Cuña horizontal)" to "Decena (10)",
                "Espacio en blanco / Doble cuña" to "Separador posicional primitivo"
            ),
            historicalImpact = "Su base 60 perdura hoy en la división del tiempo (60 segundos = 1 minuto, 60 minutos = 1 hora) y en la geometría del círculo (360 grados = 6 x 60). Servía para repartir cosechas y calcular movimientos astronómicos.",
            iconEmoji = "🌾"
        ),
        CivilisationInfo(
            id = "egipto",
            name = "Antiguo Egipto",
            period = "c. 3000 a.C. - 300 a.C.",
            baseNumber = 10,
            systemType = "Aditivo (Decimal)",
            summary = "Sistema decimal aditivo con jeroglíficos específicos para cada potencia de 10 (desde 1 hasta 1,000,000). Los símbolos se repetían hasta 9 veces sin importar el orden exacto.",
            keySymbols = listOf(
                "| (Trazo o vara)" to "1 (Unidad)",
                "∩ (Talón o asa de cesta)" to "10 (Decena)",
                "𓍢 (Cuerda enrollada)" to "100 (Centena)",
                "𓆸 (Flor de loto)" to "1,000 (Millar)",
                "𓂭 (Dedo índice curvado)" to "10,000",
                "𓆐 (Renacuajo o rana)" to "100,000",
                "𓁨 (Hombre asombrado con brazos alzados)" to "1,000,000"
            ),
            historicalImpact = "Fundamental para los agrimensores y escribas tras las periódicas inundaciones del río Nilo para delimitar tierras, censar granos y construir pirámides.",
            iconEmoji = "🏛️"
        ),
        CivilisationInfo(
            id = "roma",
            name = "Imperio Romano",
            period = "c. 753 a.C. - 476 d.C.",
            baseNumber = 10,
            systemType = "Aditivo con principio sustractivo",
            summary = "Utiliza 7 letras mayúsculas con valores fijos: I (1), V (5), X (10), L (50), C (100), D (500), M (1000). Si una letra de menor valor se ubica a la izquierda de una mayor, se resta (ej. IV = 4, IX = 9, XC = 90).",
            keySymbols = listOf(
                "I" to "1",
                "V" to "5",
                "X" to "10",
                "L" to "50",
                "C" to "100",
                "D" to "500",
                "M" to "1000"
            ),
            historicalImpact = "Excelente para registros monumentales en piedra, leyes y monedas, pero con GRAVES limitaciones para el cálculo escrito. Multiplicar o dividir en números romanos era una tarea titánica sin ayuda del ábaco manual romano.",
            iconEmoji = "⚔️"
        ),
        CivilisationInfo(
            id = "mayas",
            name = "Civilización Maya (Mesoamérica)",
            period = "c. 2000 a.C. - 1500 d.C.",
            baseNumber = 20,
            systemType = "Posicional Vigesimal Vertical",
            summary = "Sistema posicional vertical estratificado en niveles de potencias de 20 (20^0=1, 20^1=20, 20^2=400, 20^3=8000). Usaban tres signos: el punto (1), la barra (5) y el caracol o concha (0).",
            keySymbols = listOf(
                "• (Punto)" to "Valor 1 (máximo 4 puntos por nivel)",
                "— (Barra o raya)" to "Valor 5 (máximo 3 barras por nivel)",
                "🐚 (Caracol / Concha)" to "Cero: plenitud, inicio y ciclo completo"
            ),
            historicalImpact = "Fueron de las primeras culturas en la historia universal en concebir el cero posicional y conceptual. Les permitió cálculos calendáricos astronómicos con precisión de fracciones de día a lo largo de milenios.",
            iconEmoji = "🐚"
        ),
        CivilisationInfo(
            id = "indoarabigo",
            name = "Sistema Indoarábigo y Fibonacci",
            period = "c. Siglo V d.C. (India) - 1202 d.C. (Europa)",
            baseNumber = 10,
            systemType = "Posicional Decimal con Cero Universal",
            summary = "Nacido en la India antigua con los números Brahmi y el concepto de 'śūnya' (vacío/cero), adoptado por sabios islámicos como Al-Juarismi ('sifr'). Leonardo de Pisa (Fibonacci) lo introdujo a Europa en 1202 con el libro 'Liber Abaci'.",
            keySymbols = listOf(
                "0 (Cero / Sifr)" to "Marcador de posición vacía y valor nulo",
                "1, 2, 3, 4, 5, 6, 7, 8, 9" to "Cifras con valor relativo a su posición",
                "Liber Abaci (1202)" to "Demostró la superioridad para el comercio"
            ),
            historicalImpact = "Revolucionó el comercio, la contabilidad por partida doble y el nacimiento de la ciencia moderna. Hizo obsoletos los pesados sistemas aditivos y permitió realizar operaciones por algoritmos en lápiz y papel.",
            iconEmoji = "📜"
        )
    )

    val classificationCards = listOf(
        ClassificationCard(
            id = "c1",
            title = "Valor fijo de cada símbolo",
            description = "Cada grafía vale siempre lo mismo, sin importar en qué lugar de la cifra se escriba.",
            isPositional = false,
            systemExample = "Ej. Egipto (∩ siempre vale 10) o Roma (X siempre vale 10)"
        ),
        ClassificationCard(
            id = "c2",
            title = "Dependencia de la posición",
            description = "El valor de un dígito se multiplica por una potencia de la base según su orden.",
            isPositional = true,
            systemExample = "Ej. En 222, el primer 2 vale 200, el segundo 20 y el tercero 2."
        ),
        ClassificationCard(
            id = "c3",
            title = "Necesidad indispensable del Cero",
            description = "Requiere obligatoriamente un símbolo para indicar la ausencia de unidades en una columna.",
            isPositional = true,
            systemExample = "Ej. El caracol maya o el 0 indoarábigo (diferencia entre 25 y 205)."
        ),
        ClassificationCard(
            id = "c4",
            title = "Saturación de símbolos con cifras grandes",
            description = "Para escribir números grandes se necesita inventar símbolos nuevos o repetir decenas de veces.",
            isPositional = false,
            systemExample = "Ej. Para escribir 3,888 en romano se usan 15 letras (MMMDCCCLXXXVIII)."
        ),
        ClassificationCard(
            id = "c5",
            title = "Operaciones escritas eficientes",
            description = "Permite sumar, restar, multiplicar y dividir alineando columnas con algoritmos estándar.",
            isPositional = true,
            systemExample = "Ej. Algoritmo estándar de la multiplicación de Al-Juarismi y Fibonacci."
        ),
        ClassificationCard(
            id = "c6",
            title = "Imposibilidad de cálculo algorítmico directo",
            description = "Las operaciones matemáticas escritas son tan complejas que obligan a usar ábacos físicos.",
            isPositional = false,
            systemExample = "Ej. Los romanos calculaban en ábacos de ranuras y solo anotaban el resultado final."
        ),
        ClassificationCard(
            id = "c7",
            title = "Lectura por suma simple de valores",
            description = "La cantidad total se obtiene simplemente sumando el valor intrínseco de cada símbolo presente.",
            isPositional = false,
            systemExample = "Ej. III = 1 + 1 + 1 = 3; CL = 100 + 50 = 150."
        ),
        ClassificationCard(
            id = "c8",
            title = "Potencias crecientes de la base",
            description = "Cada columna representa sucesivamente Base^0, Base^1, Base^2, Base^3...",
            isPositional = true,
            systemExample = "Ej. Maya (1, 20, 400, 8000) o Indoarábigo (1, 10, 100, 1000)."
        )
    )

    val quizQuestions = listOf(
        QuizQuestion(
            id = 1,
            question = "¿Cuál es la diferencia fundamental entre un sistema de numeración aditivo y uno posicional?",
            options = listOf(
                "En el aditivo los números solo son pares y en el posicional solo impares.",
                "En el aditivo el valor de cada símbolo es fijo y se suman; en el posicional el valor depende del lugar que ocupa.",
                "El sistema aditivo siempre usa el cero y el posicional prescinde totalmente de él.",
                "El sistema aditivo fue inventado en Europa y el posicional en América exclusivamente."
            ),
            correctIndex = 1,
            explanation = "En los sistemas aditivos (como el egipcio o el romano), cada símbolo conserva un valor inmutable sin importar su posición. En los sistemas posicionales (como el maya, babilonio o indoarábigo), el valor relativo se multiplica por la potencia de la base según su columna o nivel.",
            civilizationContext = "Progresión 2 - Clasificación de sistemas"
        ),
        QuizQuestion(
            id = 2,
            question = "¿Por qué el sistema de numeración romano presentaba serias limitaciones para operaciones escritas como multiplicación y división?",
            options = listOf(
                "Porque los romanos no conocían los números mayores a 1,000.",
                "Porque al carecer de valor posicional y cero, no existían columnas alineables para ejecutar algoritmos directos sobre papel.",
                "Porque estaba prohibido por edicto imperial hacer cuentas matemáticas.",
                "Porque solo se podía utilizar en monumentos de piedra pero no en pergaminos."
            ),
            correctIndex = 1,
            explanation = "En el sistema romano, la combinación de reglas aditivas y sustractivas (ej. IV, IX, XC) y la ausencia de valor posicional impedían aplicar reglas mecánicas de acarreos por columnas. Por ello, los banqueros y comerciantes romanos utilizaban obligatoriamente el ábaco manual para calcular y solo anotaban el resultado final en números romanos.",
            civilizationContext = "Sistema Romano vs Indoarábigo"
        ),
        QuizQuestion(
            id = 3,
            question = "En el sistema vigesimal maya, ¿cuál es el valor correspondiente a la tercera posición vertical (Nivel 3, de abajo hacia arriba)?",
            options = listOf(
                "60 (Base 20 x 3)",
                "100 (Centena decimal)",
                "400 (20 al cuadrado: 20 x 20)",
                "8,000 (20 al cubo)"
            ),
            correctIndex = 2,
            explanation = "El sistema maya se lee de abajo hacia arriba en potencias de 20: Nivel 1 = 20^0 (unidades x 1); Nivel 2 = 20^1 (veintenas x 20); Nivel 3 = 20^2 (cuatrocentenas x 400); Nivel 4 = 20^3 (ochomil x 8,000).",
            civilizationContext = "América - Sistema Maya"
        ),
        QuizQuestion(
            id = 4,
            question = "¿Qué concepción filosófica y matemática tenía el símbolo del caracol (cero) en la cosmovisión maya?",
            options = listOf(
                "Significaba destrucción, pérdida económica y desgracia.",
                "Representaba no solo la ausencia de cantidad, sino completitud, cierre de un ciclo e inicio de una nueva etapa.",
                "Era únicamente un dibujo decorativo sin ningún valor matemático.",
                "Representaba un tributo exclusivo a las deidades del mar."
            ),
            correctIndex = 1,
            explanation = "Para los mayas, el cero no era simplemente la nada o el vacío, sino la plenitud (completitud) de un ciclo vigesimal que se integraba para permitir el nacimiento del siguiente orden astronómico y numérico.",
            civilizationContext = "El Cero Maya"
        ),
        QuizQuestion(
            id = 5,
            question = "¿Qué base numérica utilizaban los escribas de Mesopotamia en sus tablillas cuneiformes y cuál es su herencia moderna?",
            options = listOf(
                "Base 2 (Binario) heredado a las computadoras modernas.",
                "Base 60 (Sexagesimal), cuya herencia perdura en los 60 segundos del minuto, 60 minutos de la hora y 360 grados del círculo.",
                "Base 5 (Quinario) usado hoy en el sistema métrico.",
                "Base 12 (Duodecimal) usado solo para medir huevos."
            ),
            correctIndex = 1,
            explanation = "La civilización sumerio-babilónica introdujo la base 60 (sexagesimal). Por su enorme divisibilidad (divisible entre 2, 3, 4, 5, 6, 10, 12, 15, 20, 30), fue ideal para fraccionar el tiempo y los círculos celestes, legado que usamos diariamente en nuestros relojes.",
            civilizationContext = "Mesopotamia Cuneiforme"
        ),
        QuizQuestion(
            id = 6,
            question = "¿Cuál es la función indispensable que cumple el CERO en los sistemas de numeración posicionales?",
            options = listOf(
                "Servir como adorno para que el número se vea más largo.",
                "Actuar como marcador de posición vacía para que las cifras no cambien de magnitud (ej. distinguir 25 de 205 o 2050).",
                "Permitir multiplicar por 10 sin hacer cálculos.",
                "Impedir que se escriban números impares."
            ),
            correctIndex = 1,
            explanation = "Sin un símbolo de cero como guardián o marcador de posición (place-holder), un sistema posicional colapsa en la ambigüedad, pues no se podría distinguir entre dos unidades y dos decenas (2 vs 20) o entre 35 y 305.",
            civilizationContext = "El Cero Posicional"
        ),
        QuizQuestion(
            id = 7,
            question = "¿Cuál fue el impacto trascendental de la obra 'Liber Abaci' (1202) de Leonardo de Pisa (Fibonacci)?",
            options = listOf(
                "Demostró que el sistema de numeración romano era superior para la astronomía.",
                "Prohibió el uso del ábaco en toda Europa por considerarlo pagano.",
                "Demostró la superioridad y rapidez de los números indoarábigos y el cero para los comerciantes frente a los números romanos.",
                "Inventó el papel moneda y los primeros bancos italianos."
            ),
            correctIndex = 2,
            explanation = "En 'Liber Abaci' (Libro del Ábaco o del Cálculo), Fibonacci explicó las 9 cifras indias y el signo 'zephirum' (cero), mostrando cómo los mercaderes podían realizar cálculos comerciales de intereses, cambios de moneda y repartos en fracciones de tiempo comparado con la lenta notación romana.",
            civilizationContext = "El Legado de Fibonacci"
        ),
        QuizQuestion(
            id = 8,
            question = "¿Cuál es la diferencia fundamental entre el ábaco tradicional y el Nepohualtzitzin prehispánico?",
            options = listOf(
                "El ábaco tradicional funciona en base 10 (decimal) y el Nepohualtzitzin en base 20 (vigesimal mesoamericano).",
                "El ábaco tradicional tiene pilas eléctricas y el Nepohualtzitzin es de madera.",
                "El Nepohualtzitzin solo sirve para contar granos de maíz y no puede sumar.",
                "El ábaco tradicional fue inventado por los egipcios y el Nepohualtzitzin por los romanos."
            ),
            correctIndex = 0,
            explanation = "El Nepohualtzitzin es un instrumento de cómputo vigesimal (base 20) desarrollado por las culturas mesoamericanas (mayas y nahuas). Cada barra posee 7 cuentas (3 superiores con valor 5 cada una, y 4 inferiores con valor 1 cada una), sumando hasta 19 antes de subir de nivel, a diferencia del ábaco decimal basado en potencias de 10.",
            civilizationContext = "Herramientas de Cálculo: Ábaco y Nepohualtzitzin"
        )
    )

    // Helper conversion functions
    fun toRoman(number: Int): String {
        if (number <= 0 || number > 3999) return "Fuera de rango (1-3999)"
        val values = intArrayOf(1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1)
        val symbols = arrayOf("M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I")
        val sb = StringBuilder()
        var num = number
        for (i in values.indices) {
            while (num >= values[i]) {
                sb.append(symbols[i])
                num -= values[i]
            }
        }
        return sb.toString()
    }

    data class MayaLevel(
        val levelIndex: Int, // 0 for x1, 1 for x20, 2 for x400, 3 for x8000
        val multiplier: Int,
        val valueInLevel: Int, // 0..19
        val barsCount: Int,    // valueInLevel / 5
        val dotsCount: Int,    // valueInLevel % 5
        val isZero: Boolean    // valueInLevel == 0
    )

    fun toMaya(number: Int): List<MayaLevel> {
        if (number == 0) {
            return listOf(MayaLevel(0, 1, 0, 0, 0, true))
        }
        var temp = number
        val levels = mutableListOf<MayaLevel>()
        var levelIdx = 0
        var multiplier = 1

        while (temp > 0 || levels.isEmpty()) {
            val rem = temp % 20
            levels.add(
                MayaLevel(
                    levelIndex = levelIdx,
                    multiplier = multiplier,
                    valueInLevel = rem,
                    barsCount = rem / 5,
                    dotsCount = rem % 5,
                    isZero = rem == 0
                )
            )
            temp /= 20
            levelIdx++
            multiplier *= 20
        }
        return levels.reversed() // Highest level on top
    }

    data class EgyptianSymbols(
        val millions: Int, // 1,000,000
        val hundredThousands: Int, // 100,000
        val tenThousands: Int, // 10,000
        val thousands: Int, // 1,000
        val hundreds: Int, // 100
        val tens: Int, // 10
        val units: Int // 1
    )

    fun toEgyptian(number: Int): EgyptianSymbols {
        var num = number
        val millions = num / 1_000_000
        num %= 1_000_000
        val hundredThousands = num / 100_000
        num %= 100_000
        val tenThousands = num / 10_000
        num %= 10_000
        val thousands = num / 1_000
        num %= 1_000
        val hundreds = num / 100
        num %= 100
        val tens = num / 10
        val units = num % 10

        return EgyptianSymbols(millions, hundredThousands, tenThousands, thousands, hundreds, tens, units)
    }

    data class BabylonianLevel(
        val sexagesimalPower: Int,
        val multiplier: Int,
        val value: Int, // 0..59
        val tensCount: Int, // <
        val unitsCount: Int // T
    )

    fun toBabylonian(number: Int): List<BabylonianLevel> {
        if (number == 0) return listOf(BabylonianLevel(0, 1, 0, 0, 0))
        var temp = number
        val list = mutableListOf<BabylonianLevel>()
        var power = 0
        var mult = 1
        while (temp > 0) {
            val rem = temp % 60
            list.add(
                BabylonianLevel(
                    sexagesimalPower = power,
                    multiplier = mult,
                    value = rem,
                    tensCount = rem / 10,
                    unitsCount = rem % 10
                )
            )
            temp /= 60
            power++
            mult *= 60
        }
        return list.reversed()
    }
}
