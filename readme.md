# SnoopiX
*__Etat Actuel du projet :__ Non fonctionnel :*  
*La structure de l'application est codée et fonctionnelle (Association des bonnes règles avec les bon fichiers.*  
*L'implémentation concrète des validations reste à coder.*

**SnoopiX** est un petit utilitaire destiné à parcourir récursivement des répertoires, pour valider des documents XML selon différentes grammaires. 
 
Il fonctionne selon un principe de _Règles_ basées sur la concordance ou non d'un fichier : 

 - à une expression régulière (Nom du fichier)
 - À une assertion
   booléenne exprimée en XPath (Non encore implémenté)
   
Ces règles sont définie hiéarchiquement, et peuvent être surchargées pour chaque sous-répertoire.  
Pour chaque fichier, la règle la  plus spécifique est appliquée.

  
## Validation des documents

Dans chaque règle, on demande une ou plusieurs étapes de validation. 
Toutes les étapes de validation doivent être concluantes pour que le document soit considéré comme valide. 
 
Trois type d'étapes peuvent être définis : 

- L'étape simple, qui confronte le document à une grammaire, exprimée dans n'importe lequel des langages standards (DTD, XSD, RNG, NVDL... et désignée par une URI et un type. 
  <pre><code>&lt;validate href="toto.dtd" /&gt;</code></pre>

 
 
- L'étape d'autovalidation, c'est un type particulier d'étape simple. Au lieu de spécifier l'URI d'une grammaire, on valide le document selon son en-tête 
    <pre><code>&lt;validate /&gt;</code></pre>

 
 
- L'étape composée (choose), qui est elle-même constituée de plusieurs étapes simples. 
Si une des sous étapes renvoie valide, alors l'étape composée renvoie valide. 
	<pre><code>
    &lt;choose&gt; 
        &lt;validate href="foo.nvdl" /&gt;  
        &lt;validate href="bar.xsd" /&gt;  
    &lt;/choose&gt;
	</code></pre>

  
 
 
On peut spécifier les règles via un/des fichier(s) XML. Le 

- En passant un fichier unique en paramètre au lancement 
- En passant en paramètre un nom de fichier qui sera recherché récursivement dans chaque sous-répertoire du répertoire source.  
  Les règles définies dans le contexte d'un sous-répertoire surchargent les règles du dossier parent. (fonctionnement similaire au .htaccess des serveurs apache)  
  _Ce deuxième comportement est le compartement par défaut. le nom de fichier par défaut est "validation.xml"_

