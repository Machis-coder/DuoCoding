export interface Question {
    type: number,
    description: string,
    answer: string,
    responses: Response[],
}