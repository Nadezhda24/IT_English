### Что хранится в папке data?
В данной папке находятся исходный текст каждой статьи и картинки для статей. 


### Подробнее, что куда тыкать?
В *data* созданы следующие директории:

- `terms` - содержит файлы с описанием терминов.
- `programming_languages` содержит файлы с описанием языков программирования.
- `professions` - содержит файлы с описанием профессий.
- `trends` - содержит файлы с описанием будущих трендов.

В каждой из этих папок есть две *поддиректории*:
- `texts` - хранит текст статей данного раздела
- `images` - хранит картинки статей 
Название статьи и соответствующей ей картинки должно полностью совпадать, чтобы было понятно, что к чему относится.

### Формат файлов
Все файлы должны иметь расширение `.html`, к сожалению. Свято верю, что потомки опомнятся и перейдут на формат `markdown`.

#### Формат статей

Все заголовки должны быть заключены в тег `<h3></h3>`, параграфы - в тег `<p></p>`.
Для выделения слова жирным шрифтом, используйте тег `<b></b>`.

#### Термины
Статьи в разделе "термины" описываются в соответствии со следующим форматом:

```
*Description*
    `your description of your term here`
    ...
    Analogue of `described type` in Russian: *<1st>*, *<2nd>*, ... *<n'th>* // In case there're more than one similiar terms in Russian

...
*Arbitrary headers*
...

See also 
*<1st>*, *<2nd>*, ... *<n'th>* // Cross-references to other application materials
```
Также вы можете скопировать этот шаблон из данного [файла](templates/term.html).

Upd. На мой взгляд, будет полезно добавлять аналогичные названия терминов на русском, которые уже устоялись в русской технической литературе.
Машинный перевод данных терминов может сбить читателя с толку. Например `data race` обозначает `состояние гонки`, а не `гонка данных`.

#### Языки программирования
Статьи в разделе "языки программирования" описываются в соответствии со следующим форматом:

```
*Creators*
    `Here you write about the creators of the programming language`
*Date of the first release*
    `A few words about the date of the first release in form: yyyy-mm-dd (days are optional)`
*Brif description*
    `Say about pronunciation. For instance, C# can be pronunced like "see sharp"`
    ...
    `Some general info`
*Purpose*
    `Say about the purpose of this language. In case the language is multipurpose, say about it too`.

*Products written with (insert the language name here)*
    `list of products`

-- This section is optional 
*Some examples*
    `Here you can add some code examples written in this language`.

See also 
*<1st>*, *<2nd>*, ... *<n'th>* // Cross-references to other application materials
```

Также вы можете скопировать этот шаблон из данного [файла](templates/programming_language.html).




### TODO-таблица

Нами была создана [TODO-таблица](https://docs.google.com/spreadsheets/d/14_3-DDZsczMhaSFRzaL8bGDGboNCUSXn4FNdcgW2EWQ/edit#gid=0), в которой мы отмечаем, какие вещи мы уже описали и какие ещё собираемся описать. Таблица всё еще дополняется!

#### Отчётность в таблице
- `+` отмечаются элементы, которые были реализованы.
- `#` отмечаются элементы, которые кем-либо зарезервированны для создания.
