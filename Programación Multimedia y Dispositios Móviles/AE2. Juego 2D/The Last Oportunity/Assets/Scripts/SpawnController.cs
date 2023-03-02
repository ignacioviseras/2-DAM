using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnController : MonoBehaviour
{

    public GameObject enemyPrefab;//elemento donde guardaremos el zombie

    // Start is called before the first frame update
    void Start()
    {
        StartCoroutine("SpawnEnemy");//llamamos a la funcion para que se ejecute
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    IEnumerator SpawnEnemy()//funcion que genera pausas en su ejecucion
    {
        yield return new WaitForSeconds(2f);//pausa de 2 segunds
        while(true)
        {
            Instantiate(enemyPrefab, transform.position, Quaternion.identity);//generamos el zombie
            yield return new WaitForSeconds(8f);//pausa de 8 segundos para que se vuelva a generar un zombie
        }
    }
}