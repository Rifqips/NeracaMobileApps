package id.softnusa.core.domain.util.event

sealed class TransactionEvent {

    data class ShowSnackbar(
        val message: String
    ) : TransactionEvent()

    object TransactionCreated : TransactionEvent()

}