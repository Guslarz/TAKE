using System.ComponentModel.DataAnnotations;

namespace MvcNews.Models
{
    public class NewsItem
    {
        public int Id { get; set; }

        [Timestamp]
        public byte[]? RowVersion { get; set; }

        [DataType(DataType.Date)]
        public DateTime Timestamp { get; set; }

        [Required]
        [StringLength(140, MinimumLength = 5)]
        public string Text { get; set; } = string.Empty;
    }
}
