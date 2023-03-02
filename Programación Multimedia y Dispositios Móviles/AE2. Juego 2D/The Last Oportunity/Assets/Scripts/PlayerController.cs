using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;//libreria para el text

public class PlayerController : MonoBehaviour
{
    public float speed = 3.0f;
    public float jump = 8.0f;
    private float movementX;//declaramos la variable de movimiento
    private SpriteRenderer flipPlayer;//declaramos variable para acceder al componente
    private Rigidbody2D playerRb;//declaramos variable para acceder al componenete
    private bool isGround = true;//booleano para saber si esta en el suelo
    private AudioSource audioSource;
    public AudioClip audioCoin, audioJump, audioLose;
    public GameObject camara;//camaba para cuando el player muera siga mostrando el juego
    private Animator animatorPlayer, zombie;
    private bool daño;
    public int empuje;
    private int distancia;
    private int vida = 3;
    public Text vidasText;
    public GameObject panelGameOver;



    // Start is called before the first frame update
    void Start()
    {
        flipPlayer = GetComponent<SpriteRenderer>();//optenemos el componente Sprite Render
        playerRb = GetComponent<Rigidbody2D>();
        audioSource = GetComponent<AudioSource>();//instanciamos la variable audioSource
        animatorPlayer = GetComponent<Animator>();
        zombie = GameObject.FindWithTag("Enemy").GetComponent<Animator>();
        FindObjectOfType<camionController>().lives = vida;
    }

    // Update is called once per frame
    void Update()
    {
        //movimiento del personajer sera detectado por las teclas a d o der izq
        //cuando sean pulsadas añadira -1 o 1
        //para hacer que el movimiento del personaje sea mas rapido lo multiplicamos por un numero
        //cuanto mas grande este mas rapido ira

        //Time.deltaTime limita la velocidad de frames y lo pasa a segundos
        Daño();
        if(!daño)//podremos acceder si no estamos recibiendo daño
        {
            if (vida > 0)
            {
                float movementX = Input.GetAxis("Horizontal");//como nos vamos a mover en todo momento tendra que estar en Update la recepcion
                animatorPlayer.SetFloat("walk", Mathf.Abs(movementX));
                if (movementX > 0)//si se mueve a la derecha
                {
                    flipPlayer.flipX = false;

                }
                if (movementX < 0)//si se mueve a la izquierda
                {
                    flipPlayer.flipX = true;

                }
                transform.position += new Vector3(movementX, 0, 0) * speed * Time.deltaTime;

                if ((Input.GetKeyDown(KeyCode.UpArrow) || Input.GetKeyDown(KeyCode.W)) && isGround)//estamos comprobando si se ha pulsado la tecla "flecha hacia arriba"
                {
                    
                    audioSource.PlayOneShot(audioJump);
                    //indicaremos que el personaje sufrira una fuerza hacia arriba
                    //diremos de cuanto sera esa fuerza en jump
                    //y diremos que tipo de fuerza sera en este caso de impulso
                    playerRb.AddForce(Vector2.up * jump, ForceMode2D.Impulse);

                    isGround = false;
                    animatorPlayer.SetBool("jump", isGround);

                }

                if (Input.GetKeyDown(KeyCode.F))//accion de atacar
                {
                    animatorPlayer.SetTrigger("attack");
                }
            }
            else
            {
                panelGameOver.SetActive(true);
                animatorPlayer.SetTrigger("dead");
                audioSource.PlayOneShot(audioLose);
            }
            
        }
        
    }

    private void OnCollisionEnter2D(Collision2D collision)//nos avisa SI el player esta tocando el suelo
    {
        if (collision.gameObject.tag == "Ground" && collision.gameObject.tag != "Pared")//comprobamoss que con lo que se esta colisionando es el suelo
        {
            isGround = true;
            animatorPlayer.SetBool("jump", isGround);
        }

        if (collision.gameObject.tag == "Enemy")
        {
            if (vida > 0)
            {
                
                animatorPlayer.SetTrigger("daño");
                daño = true;
                if (transform.position.x > collision.transform.position.x)
                {
                    //transform.position += new Vector3(empuje, 0, 0) * speed * Time.deltaTime;
                    if (vida > 0)
                    {
                        distancia = empuje;
                        vida--;
                        FindObjectOfType<camionController>().lives = vida;
                        vidasText.text = vida.ToString() + " / 3";
                    }

                }
                else
                {
                    if (vida > 0)
                    {
                        distancia = empuje * -1;
                        vida--;
                        FindObjectOfType<camionController>().lives = vida;
                        vidasText.text = vida.ToString() + " / 3";
                    }
                    //transform.position += new Vector3(empuje, 0, 0) * speed * Time.deltaTime;
                }
                //camara.transform.parent = null;//independizamos la camara para que se siga viendo
                //Destroy(gameObject);//destruimos al player
            }
        }

        if (collision.gameObject.tag == "CaidaLibre")
        {
            vida = 0;
        }

    }

    private void OnCollisionExit2D(Collision2D collision)//nos avisa si el player NO esta tocando el suelo
    {
        //comprobamoss que con lo que se esta colisionando es el suelo
        //!!!!   Como el metodo es colisionExit se esta comprobando que no hay colision con ese elemento    !!!!!
        if (collision.gameObject.tag == "Ground")
        {
            isGround = false;
        }
    }

    public void CoinSound() 
    {
        audioSource.PlayOneShot(audioCoin);//esto pone en marcha el audio de la moneda hay que llamarlo en CoinController.cs
    }


    public void Daño()
    {
        if (daño)//si recibe daño el personaje 
        {
            //Space.World direccion universal
            transform.Translate(Vector3.right * distancia * Time.deltaTime, Space.World);//moveremos al personaje cuando reciba daño

        }
    }

    public void Fin_Daño()
    {
        daño = false;
    }
}
