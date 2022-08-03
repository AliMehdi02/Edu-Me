import './styles/filter-topics.css';

function FilterExperience({chosenExp}) {
    const experienceList = ['Novice', 'Beginner', 'Intermediate', 'Advanced'];

    return (
        <div className='filter-topics-container'>
            {experienceList.map((element, index) => {
                return <button key={index} className='filter-topic-btn' 
                        id={`filter-topic-btn-${index}`}
                        onClick={(event) => {
                            if (event.target.innerText.charAt(0) === '+') {
                                event.target.innerText = event.target.innerText.substring(2);
                                event.target.style = 'background: rgb(207, 207, 207);';
                                chosenExp[event.target.id.split('-')[3]] = false;
                            }
                            else {
                                event.target.innerText = '+ ' + event.target.innerText;
                                event.target.style = 'background: rgb(200, 142, 255);';
                                chosenExp[event.target.id.split('-')[3]] = true;
                            }
                        }}>{element}</button>
            })}
        </div>
    );
}

export default FilterExperience;
