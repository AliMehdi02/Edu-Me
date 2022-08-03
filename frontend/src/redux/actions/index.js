
export const toggleSearchLoadingAction = () => {
    return ({
        type: 'LOADING_TOGGLE'
    });
};

export const isSearchPostAction = (bool) => {
    return ({
        type: 'IS_SEARCH_POST',
        payload: bool
    });
};
