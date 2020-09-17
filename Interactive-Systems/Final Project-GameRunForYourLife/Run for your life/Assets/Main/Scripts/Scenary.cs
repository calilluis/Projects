using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Scenary : MonoBehaviour
{
    public float runSpeed;
    private ScenarySpawner obstacleSpawner;
    public int difficulty;
    public float dropDestroyDelay;
    private Collider myCollider;
    private Rigidbody myRigidbody;

    int diflevel = 0;
    // public float heartOffset; 
    // public GameObject heartPrefab; 

    public void SetSpawner(ScenarySpawner spawner)
    {
        obstacleSpawner = spawner;
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


    public void addDificulty(int add) {
        difficulty++;
        runSpeed = runSpeed + 100 * (difficulty / 2);
    }
    // Update is called once per frame
    void Update()
    {
        transform.Translate(-Vector3.forward * runSpeed * Time.deltaTime);
    }

    private void TriggerDestroy()
    {
        Destroy(gameObject,3);
    }


    private void OnTriggerEnter(Collider other)
    {
        if (other.CompareTag("Trigger"))
        {
            TriggerDestroy();
        }
    }
}
