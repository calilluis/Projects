using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.Video;

public class LevelLoader
{
    public static IEnumerator LoadLevel(int levelIndex)
    {
        yield return new WaitForSeconds(3f);

        SceneManager.LoadScene(levelIndex);
    }
}