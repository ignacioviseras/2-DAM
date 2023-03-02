using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlataformaMovil : MonoBehaviour
{
    
    public Transform target;//variable para la posicion del destino
    private float speed = 0.70f;//variable para velocidad de la plataforma
    private Vector3 start, end;

    // Start is called before the first frame update
    void Start()
    {
        start = transform.position;//posicion de inicio de la plataforma
        end = target.position;//posicion final de la plataforma
    }

    // Update is called once per frame
    void Update()
    {
        //MoveTowards -> te vas a mover desde transform.position hasta target.position a esta velocidad speed * Time.deltaTime
        transform.position = Vector3.MoveTowards(transform.position, target.position, speed * Time.deltaTime);

        //
        if (transform.position == target.position)//si llegamos al destino
        {
            if (target.position == end)//si el destino es end
            {
                target.position = start;//cambiamos el destino de sitio para que cambie de sentido (para que vuelva)
            }
            else 
            {
                target.position = end;
            }
        }
    }

    //Usaremos esto para detectar si el personaje HA ENTRADO a la plataforma
    private void OnCollisionEnter2D(Collision2D collision)
    {
        collision.transform.parent = transform;//hacemos que el player sea hijo de la plataforma para que se mueva con esta.
    }

    //Usaremos esto para detectar si el personaje HA SALIDO de la plataforma
    private void OnCollisionExit2D(Collision2D collision)
    {
        collision.transform.parent = null;//cuando el player sale de la plataforma ya no es hijo de la plataforma
    }
}
