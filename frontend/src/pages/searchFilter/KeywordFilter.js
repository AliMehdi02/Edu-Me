import './styles/keyword-filter.css';

function KeywordFilter({tags, setTags}) {

    const addTag = e => {
        if (e.key === 'Enter') {
            if (e.target.value.length > 0) {
                setTags([...tags, e.target.value]);
                e.target.value = '';
            }
        }
        if (e.key === 'Backspace' && e.target.value.length === 0) {
            setTags(tags.slice(0, tags.length - 1));
        }
    };

    const removeTag = (removedTag) => {
        const newTags = tags.filter((tag) => tag !== removedTag);
        setTags(newTags);
    };

    return (
        <div>
            <p className='tags-label'>Tags:</p>
            <div className="tag-container">
                {tags.map((tag, index) => {
                    return (
                        <div key={index} className="tag">
                            {tag} <span onClick={() => removeTag(tag)}>
                                        <i className="far fa-times-circle"></i>
                                  </span>
                        </div>
                    );
                })}

                <input onKeyDown={addTag}/>
            </div>
        </div>
    );
}

export default KeywordFilter;
