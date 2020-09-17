using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Sheep : MonoBehaviour
{
    public float runSpeed;
    public float gotHayDestroyDelay;
    private bool hitByHay;
    private SheepSpawner sheepSpawner;

    public float dropDestroyDelay ; // 1
    private Collider myCollider; // 2
    private Rigidbody myRigidbody;
    public float heartOffset; // 1
    public GameObject heartPrefab; // 2

    public void SetSpawner(SheepSpawner spawner)
    {
        sheepSpawner = spawner;
    }


    void Start()
    {
        myCollider = GetComponent<Collider>();
        myRigidbody = GetComponent<Rigidbody>();
    }

    // Update is called once per frame
    void Update()
    {
        transform.Translate(Vector3.forward * runSpeed * Time.deltaTime);
    }

    private void Drop(){
        sheepSpawner.RemoveSheepFromList (gameObject);
        myRigidbody .isKinematic = false; 
        myCollider .isTrigger = false; 
        Destroy(gameObject, dropDestroyDelay ); 
        GameStateManager.Instance.DroppedSheep();
    }
    private void HitByHay()
    {   sheepSpawner.RemoveSheepFromList (gameObject);
        hitByHay = true; // 1
        runSpeed = 0; // 2
        Destroy(gameObject, gotHayDestroyDelay); // 3 destroy sheep
        Instantiate(heartPrefab, transform.position + new Vector3(0, heartOffset, 0), Quaternion.identity);
        TweenScale tweenScale = gameObject.AddComponent<TweenScale>(); // 1
        tweenScale.targetScale = 0; // 2
        tweenScale.timeToReachTarget = gotHayDestroyDelay; // 3
        GameStateManager.Instance.SavedSheep();
    }

    private void OnTriggerEnter (Collider other) // 1
    {
        if (other.CompareTag("Hay") && !hitByHay) // 2
        {
            Destroy(other.gameObject); // 3 destroy bullet?
            HitByHay(); // 4
        }else if (other.CompareTag("DropSheep"))
        {
            Drop();
        }
    }

}
