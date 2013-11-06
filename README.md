<h2>FRANCAIS</h2>

<p>Ayant besoin d'un extracteur d'archive (<b>ZIP</b> / <b>RAR</b> / <b>7z</b>) pour un projet personnel sur la plateforme <b>Android 2.2</b>, je vous partage la librairie que j'ai créée afin de répondre à mes besoins. Je la distribue sous la license <b>LGPL 2.1</b></p>

<p>J'utilise les projets <b>andro7z</b> et <b>unrar</b> dans le code de ma libraire.</p>

<h2>ENGLISH</h2>

<p>Needing a lib for extract archive file  (<b>ZIP</b> / <b>RAR</b> / <b>7z</b>)for a personal project on the <b>Android 2.2</b> platform, I created and share my personnal library. My library is share in the <b>LGPL 2.1</b></p>

<p>I'm using the <b>andro7z</b> project and the <b>unrar</b> project for coding my library.</p>

<h2>Sample / Exemple</h2>

<blockquote>
try {
<br>ExtractFile ef = new ExtractFile(context app, archive file, output directory);
<br>ef.exec();
<br>}  catch (IOException e) {
<br>Log.e("Extract error", e.getMessage());
<br>}
</blockquote>

<h2>Links / References</h2>
<ul>
<li>andro7z  - <a href="https://code.google.com/p/andro7z/">https://code.google.com/p/andro7z/</a>
</li>
<li>junrar - <a href="https://github.com/edmund-wagner/junrar">https://github.com/edmund-wagner/junrar</a>
</li>
</ul>
