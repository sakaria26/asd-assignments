package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service

@Service
class CardService {

    private val identifierFactory: IdentifierFactory = IdentifierFactory()


    private fun generateCards(): List<Card> {
        val shuffledWords = words.shuffled()
        return shuffledWords
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { wordsChunk ->
                Card(identifierFactory.uniqueIdentifier(), wordsChunk.toWords())
            }
    }

    private fun List<String>.toWords(): List<Word> {
        return this.map { string ->
            Word().apply {
                wordItem = string
            }
        }
    }

    private val cards: List<Card> = generateCards()

    companion object {
        private const val WORDS_IN_CARD = 4
        var cardsAmount = words.size / WORDS_IN_CARD
    }

    fun getCardByIndex(index: Int): Card {
        if (index < 0 || index >= cards.size) {
            throw IllegalArgumentException("Card $index does not exist")
        }
        return cards[index]
    }


}
