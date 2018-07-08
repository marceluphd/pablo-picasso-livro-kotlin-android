package thiengo.com.br.pablopicasso.domain

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Painting(
    val name: String,
    val year: Int,
    val imageUrl: String,
    val priceInMillions: Double,
    val rating: Double,
    val details: String ) : Parcelable {

    fun getPriceBRFormat(
        moneySignLabel: String,
        millionsLabel: String ): String =

        String
            .format(
                Locale.GERMANY,
                "%s %.1f %s",
                moneySignLabel,
                priceInMillions,
                millionsLabel
            )

    constructor(source: Parcel) : this(
        source.readString(),
        source.readInt(),
        source.readString(),
        source.readDouble(),
        source.readDouble(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(name)
        writeInt(year)
        writeString(imageUrl)
        writeDouble(priceInMillions)
        writeDouble(rating)
        writeString(details)
    }

    companion object {
        /*
         * Constante criada para ser utilizada como chave de envio na
         * transferência de um objeto Painting da atividade PaitingsActivity
         * para a atividade DetailsActivity.
         * */
        const val KEY = "painting_key"

        @JvmField
        val CREATOR: Parcelable.Creator<Painting> = object : Parcelable.Creator<Painting> {
            override fun createFromParcel(source: Parcel): Painting = Painting(source)
            override fun newArray(size: Int): Array<Painting?> = arrayOfNulls(size)
        }
    }
}