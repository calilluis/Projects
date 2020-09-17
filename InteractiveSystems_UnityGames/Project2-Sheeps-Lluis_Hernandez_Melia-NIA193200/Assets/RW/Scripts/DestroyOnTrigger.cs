using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DestroyOnTrigger : MonoBehaviour
{
    public string tagFilter;
    

    private void OnTriggerEnter (Collider other) // 1 The entry in this function is authomatic. The other object will be the bullet
    {
        if (other.CompareTag(tagFilter)) // 2
        {
            Destroy(gameObject); // 3
        }
    }
    

    void Start(){
        
    }

    // Update is called once per frame
    void Update(){
        
    }
}
