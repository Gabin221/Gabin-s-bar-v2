import androidx.lifecycle.MutableLiveData

object PanierManager {
    val monPanier: MutableList<String> = mutableListOf()

    val panierSizeLiveData: MutableLiveData<Int> = MutableLiveData(0)

    init {
        panierSizeLiveData.value = monPanier.size
    }

    fun ajouterElement(element: String) {
        monPanier.add(element)
        panierSizeLiveData.value = monPanier.size
    }

    fun supprimerElement(position: Int) {
        if (position >= 0 && position < monPanier.size) {
            monPanier.removeAt(position)
            panierSizeLiveData.value = monPanier.size
        }
    }

    fun supprimerTout() {
        monPanier.clear()
        panierSizeLiveData.value = monPanier.size
    }

    fun getPanierSize(): Int {
        return monPanier.size
    }
}
