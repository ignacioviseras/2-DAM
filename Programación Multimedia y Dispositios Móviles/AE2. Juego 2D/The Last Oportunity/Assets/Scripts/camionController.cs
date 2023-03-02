using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;//libreria para el text


public class camionController : MonoBehaviour
{
    public bool win = false;
    public int points,lives;
    public GameObject victoryPanel;
    public Text livesStats, coinsStats;
    private AudioSource audioSource;
    public AudioClip audioWin;

    // Start is called before the first frame update
    void Start()
    {
        audioSource = GetComponent<AudioSource>();
    }

    // Update is called once per frame
    void Update()
    {

    }

    private void OnTriggerEnter2D(Collider2D collision)//colision cn la moneda
    {
        if (collision.tag == "Player" && win)
        {
            livesStats.text = lives.ToString();
            coinsStats.text = points.ToString();
            audioSource.PlayOneShot(audioWin);
           
            victoryPanel.SetActive(true);
        }
    }
}
