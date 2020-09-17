using System.Collections;
using System.Collections.Generic;
using System.Security.Cryptography;
using UnityEngine;

public class Coin : MonoBehaviour
{
    public float runSpeed;
    private CoinSpawner coinSpawner;

    private Collider myCollider;
    private Rigidbody myRigidbody;

    private int diflevel = 0;
    // public float heartOffset; 
    // public GameObject heartPrefab; 

    public void SetSpawner(CoinSpawner spawner)
    {
        coinSpawner = spawner;
    }

    public void updateDificulty(int dif)
    {
        diflevel = dif;
        runSpeed += (100 * diflevel);
    }

    void Start()
    {
        myCollider = GetComponent<Collider>();
        myRigidbody = GetComponent<Rigidbody>();
        diflevel = 0;
        runSpeed = 1500;
    }

    // Update is called once per frame
    void Update()
    {
        transform.Translate(-Vector3.forward * runSpeed * Time.deltaTime);
    }

    private void TriggerDestroy()
    {
        //obstacleSpawner.RemoveObstacleFromList (gameObject);
        //myRigidbody.isKinematic = false; 
        //myCollider.isTrigger = false; 
        Destroy(gameObject);

    }

    private void HitByBody()
    {
        //obstacleSpawner.RemoveObstacleFromList(gameObject);
        runSpeed = 0;
        FindObjectOfType<AudioManager>().Play("Coin");
        Destroy(gameObject);
        GameStateManager.Instance.Coined();
        
    }

    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("Body"))
        {
            HitByBody();

        }
        if (other.CompareTag("CoinTrigger"))
        {
            TriggerDestroy();
        }
    }
}
