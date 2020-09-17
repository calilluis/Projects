using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Obstacle : MonoBehaviour
{
    public float runSpeed;
    private ObstacleSpawner obstacleSpawner;
    public int difficulty = 0;
    public float dropDestroyDelay; 
    private Collider myCollider; 
    private Rigidbody myRigidbody;
    int diflevel = 0;
    
    // public float heartOffset; 
    // public GameObject heartPrefab; 

    public void SetSpawner(ObstacleSpawner spawner)
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


    public void addDificulty(int add)
    {
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
        //obstacleSpawner.RemoveObstacleFromList (gameObject);
        //myRigidbody.isKinematic = false; 
        //myCollider.isTrigger = false; 
        Destroy(gameObject, dropDestroyDelay); 
        GameStateManager.Instance.ObstacleAvoided();
    }

    private void HitByBody(){
        //obstacleSpawner.RemoveObstacleFromList (gameObject);
        runSpeed = 0;
        FindObjectOfType<AudioManager>().Play("Damage");
        Destroy(gameObject);
        GameStateManager.Instance.HitByObstacle();
    }

    private void OnTriggerEnter (Collider other) 
    {
        
        if (other.CompareTag("Body")) 
        {
            myCollider.enabled = !myCollider.enabled;
            HitByBody(); 
            //END OF GAME
        }
        
        if(other.CompareTag("Trigger") ){
            TriggerDestroy(); 
        }
    }

}
