using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EnemyController : MonoBehaviour
{
    private Transform playerPosition;//variable donde se almacenara la posicion del player
    private SpriteRenderer flipZombie;//declaramos variable para acceder al componente
    public float speed = 3.0f;
    private Animator zombie;


    // Start is called before the first frame update
    void Start()
    {
        playerPosition = GameObject.Find("Player").transform;//posicion
        flipZombie = GetComponent<SpriteRenderer>();
        zombie = GetComponent<Animator>();
        

    }

    // Update is called once per frame
    void Update()
    {
        transform.position = Vector3.MoveTowards(transform.position, playerPosition.position, speed * Time.deltaTime);
        if (playerPosition.position.x < transform.position.x)//si el personaje esta a la izq del zombie
        {
            flipZombie.flipX = true;
        }
        if (playerPosition.position.x > transform.position.x)//si el personaje esta a la izq del zombie
        {
            flipZombie.flipX = false;
        }
    }

    private void OnCollisionEnter2D(Collision2D collision)
    {
        if (collision.gameObject.tag == "CaidaLibre")
        {
            Destroy(gameObject);
        }

        if (collision.gameObject.tag == "Player")
        {
            zombie.SetTrigger("attack");
        }
    }

}
