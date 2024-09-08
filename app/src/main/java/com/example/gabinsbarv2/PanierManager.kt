import android.util.Log
import androidx.lifecycle.MutableLiveData

object PanierManager {
    val monPanier: MutableList<String> = mutableListOf()

    // LiveData qui représente la taille du panier
    val panierSizeLiveData: MutableLiveData<Int> = MutableLiveData(0) // Initialisé à 0

    init {
        // S'assure que le panier est toujours synchronisé au démarrage
        panierSizeLiveData.value = monPanier.size
    }

    fun ajouterElement(element: String) {
        monPanier.add(element)
        panierSizeLiveData.value = monPanier.size
        Log.d("PanierManager", "Element ajouté : $element, Taille du panier : ${monPanier.size}")
    }

    fun supprimerElement(position: Int) {
        if (position >= 0 && position < monPanier.size) {
            monPanier.removeAt(position)
            panierSizeLiveData.value = monPanier.size
            Log.d("PanierManager", "Element supprimé à la position $position, Taille du panier : ${monPanier.size}")
        }
    }

    fun supprimerTout() {
        monPanier.clear()
        panierSizeLiveData.value = monPanier.size
        Log.d("PanierManager", "Panier vidé, Taille du panier : ${monPanier.size}")
    }

    // Optionnel : fonction pour vérifier la taille du panier
    fun getPanierSize(): Int {
        return monPanier.size
    }
}
