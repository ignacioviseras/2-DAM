using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;//libreria para el text

public class CoinController : MonoBehaviour
{
    //al ser static estamos haciendo que la variable sea compartida por el resto de monedas
    private static int points;//contador inicia a 0 ya que aun no tenemos monedas
    public Text pointsText;
    public int valor;
    
    private PlayerController playerScrp;//Script del PlayerController.cs


    // Start is called before the first frame update
    void Start()
    {
        points = 0;
        playerScrp = FindAnyObjectByType<PlayerController>();//buscamos el Script PlayerController.cs

    }

    // Update is called once per frame
    void Update()
    {
        
    }

    private void OnTriggerEnter2D(Collider2D collision)//colision cn la moneda
    {
        if (collision.tag == "Player")//si qn colisiono cn la moneda es el player
        {
            playerScrp.CoinSound();//llamamos al metodo de PlayerController.cs para que suene el audio
            points += valor;//sumamos los puntos al recoger monedas  POINTS: 0 / 15
            pointsText.text = " POINTS: " + points.ToString() + " / 10";//accedemos al texto de la ui para añadir los puntos
            Destroy(gameObject);//destruimos la moneda que acabamos de recoger
            if (points > 10) {
                FindObjectOfType<camionController>().win = true;
                FindObjectOfType<camionController>().points = points;
            }
        }
    }
}
