<h2>FRANCAIS</h2>

<p>Ayant besoin d'un extracteur d'archive (<b>ZIP</b> / <b>RAR</b> / <b>7z</b>) pour un projet personnel sur la plateforme <b>Android 2.2</b>, je vous partage la librairie que j'ai créée afin de répondre à mes besoins. Je la distribue sous la license <b>LGPL 2.1</b></p>

<p>J'utilise les projets <b>j7zip</b> et <b>unrar</b> dans le code de ma libraire.</p>

<h3>Exemple</h3>

<code>
try {
	ExtractFile ef = new ExtractFile(Context app, <fichier archive>, <Dossier d extraction>);
	ef.exec();
} catch (IOException e) {

	Log.e("Extract error", e.getMessage());
}
</code>

<h2>ENGLISH</h2>

<p>Needing a lib for extract archive file  (<b>ZIP</b> / <b>RAR</b> / <b>7z</b>)for a personal project on the <b>Android 2.2</b> platform, I created and share my personnal library. My library is share in the <b>LGPL 2.1</b></p>

<p>I'm using the j7zip project and the unrar project for coding my library.</p>

<h3>Sample</h3>

<code>
try {
	ExtractFile ef = new ExtractFile(context app, <archive file>, <output directory>);
	ef.exec();
} catch (IOException e) {
	Log.e("Extract error", e.getMessage());
}
</code>

<h2>Links / References</h2>
<ul>
<li>
j7zip  - <a href="http://sourceforge.net/projects/p7zip/files/J7Zip/">http://sourceforge.net/projects/p7zip/files/J7Zip/</a>
</li>
<li>
junrar - <a href="https://github.com/edmund-wagner/junrar">https://github.com/edmund-wagner/junrar</a>
</li>
</ul>
